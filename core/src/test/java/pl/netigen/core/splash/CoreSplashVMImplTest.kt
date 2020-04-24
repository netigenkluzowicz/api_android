package pl.netigen.core.splash

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.netigen.core.config.AppConfig
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.SplashState


class CoreSplashVMImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var coreSplashVMImpl: CoreSplashVMImpl
    @RelaxedMockK
    private lateinit var application: Application
    @RelaxedMockK
    private lateinit var gdprConsent: IGDPRConsent
    @RelaxedMockK
    private lateinit var ads: IAds
    @RelaxedMockK
    private lateinit var interstitialAd: IInterstitialAd
    @RelaxedMockK
    private lateinit var noAdsPurchases: INoAds
    @RelaxedMockK
    private lateinit var networkStatus: INetworkStatus
    private var appConfig: AppConfig = AppConfig("", "")
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        coreSplashVMImpl = CoreSplashVMImpl(
            application,
            gdprConsent,
            ads,
            noAdsPurchases,
            networkStatus,
            coroutineDispatcherIo = Dispatchers.Main,
            appConfig = appConfig
        )
        every { ads.interstitialAd } returns interstitialAd
    }

    @After
    fun after() {
        clearAllMocks()
        Dispatchers.resetMain() //reset
        testDispatcher.cleanupTestCoroutines() // clear all
    }

    @Test
    fun `SplashVM has UNINITIALIZED state after create`() {
        assertEquals(SplashState.UNINITIALIZED, coreSplashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM has FINISHED state after onStart when noAds is active`() = runBlocking {
        setUpMocks(isNoAdsActive = true)
        coreSplashVMImpl.start()
        assertEquals(SplashState.FINISHED, coreSplashVMImpl.splashState.value)
    }


    @Test
    fun `SplashVM states when location changes to ue`() = runBlockingTest {
        setUpMocks(
            isNoAdsActive = false,
            lastKnownAdConsentStatus = AdConsentStatus.PERSONALIZED_NON_UE
        )
        val gdprConsentPublisher = getFlowPublisher { gdprConsent.requestGDPRLocation() }
        val adsPublisher = getFlowPublisher { ads.interstitialAd.load() }
        coreSplashVMImpl.start()
        assertEquals(SplashState.LOADING, coreSplashVMImpl.splashState.value)
        gdprConsentPublisher.sendBlocking(CheckGDPRLocationStatus.UE)
        assertEquals(SplashState.SHOW_GDPR_CONSENT, coreSplashVMImpl.splashState.value)
        adsPublisher.sendBlocking(true)
        assertEquals(SplashState.SHOW_GDPR_CONSENT, coreSplashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM states when noAdsPurchases noAdsActive emitted false first and true later`() = runBlockingTest {
        setUpMocks(
            isNoAdsActive = false,
            lastKnownAdConsentStatus = AdConsentStatus.UNINITIALIZED,
            gdprLocationStatus = CheckGDPRLocationStatus.NON_UE
        )

        val noAdsActivePublisher = getFlowPublisher { noAdsPurchases.noAdsActive }
        coreSplashVMImpl.start()
        assertEquals(SplashState.LOADING, coreSplashVMImpl.splashState.value)
        noAdsActivePublisher.sendBlocking(true)
        assertEquals(SplashState.FINISHED, coreSplashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM states when noAdsPurchases noAdsActive emitted false first and true later when GDPR IN UE`() = runBlockingTest {
        setUpMocks(
            isNoAdsActive = false,
            lastKnownAdConsentStatus = AdConsentStatus.UNINITIALIZED,
            gdprLocationStatus = CheckGDPRLocationStatus.UE
        )

        val noAdsActivePublisher = getFlowPublisher { noAdsPurchases.noAdsActive }
        coreSplashVMImpl.start()
        assertEquals(SplashState.SHOW_GDPR_CONSENT, coreSplashVMImpl.splashState.value)
        noAdsActivePublisher.sendBlocking(true)
        assertEquals(SplashState.FINISHED, coreSplashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM has GDPR_POP_UP status when there is first launch with no internet`() = runBlockingTest {
        setUpMocks(isConnectedOrConnecting = false)
        coreSplashVMImpl.start()
        assertEquals(SplashState.SHOW_GDPR_CONSENT, coreSplashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM isFirstLaunch == true when there is first launch with connected internet`() = runBlockingTest {
        setUpMocks(isConnectedOrConnecting = true)
        coreSplashVMImpl.start()
        assertEquals(true, coreSplashVMImpl.isFirstLaunch.value)
    }

    @Test
    fun `SplashVM finishes  after noAds true called`() = runBlocking {
        setUpMocks(isNoAdsActive = true)
        coreSplashVMImpl.start()
        assertEquals(SplashState.FINISHED, coreSplashVMImpl.splashState.value)
    }

    fun <T> getFlowPublisher(value: T? = null, stubBlock: MockKMatcherScope.() -> Flow<T>): ConflatedBroadcastChannel<T> {
        val noAdsActivePublisher: ConflatedBroadcastChannel<T> = if (value != null) ConflatedBroadcastChannel(value) else ConflatedBroadcastChannel()
        val every = every(stubBlock)
        every.returns(noAdsActivePublisher.asFlow())
        return noAdsActivePublisher
    }

    private fun setUpMocks(
        isNoAdsActive: Boolean = false,
        lastKnownAdConsentStatus: AdConsentStatus = AdConsentStatus.UNINITIALIZED,
        isConnectedOrConnecting: Boolean = true,
        loadInterstitialAdResult: Boolean = true,
        gdprLocationStatus: CheckGDPRLocationStatus = CheckGDPRLocationStatus.NON_UE

    ) {

        coEvery { noAdsPurchases.noAdsActive }.returns(flow {
            emit(isNoAdsActive)
        })
        coEvery { gdprConsent.requestGDPRLocation() }.returns(flow {
            emit(gdprLocationStatus)
        })
        coEvery { gdprConsent.adConsentStatus }.returns(flow {
            emit(lastKnownAdConsentStatus)
        })

        coEvery { ads.interstitialAd.load() }.returns(flow { emit(loadInterstitialAdResult) })
        every { networkStatus.isConnectedOrConnecting }.returns(isConnectedOrConnecting)
    }
}