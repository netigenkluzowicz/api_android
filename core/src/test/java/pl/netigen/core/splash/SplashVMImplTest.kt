package pl.netigen.core.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
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
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.SplashState



class SplashVMImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var splashVMImpl: SplashVMImpl
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
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        splashVMImpl = SplashVMImpl(gdprConsent, ads, noAdsPurchases, networkStatus, coroutineDispatcherIo = Dispatchers.Main)
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
        assertEquals(SplashState.UNINITIALIZED, splashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM has FINISHED state after onStart when noAds is active`() = runBlocking {
        setUpMocks(isNoAdsActive = true)
        splashVMImpl.onStart()
        assertEquals(SplashState.FINISHED, splashVMImpl.splashState.value)
    }


    @Test
    fun `SplashVM states when location changes to ue`() = runBlockingTest {
        setUpMocks(
            isNoAdsActive = false,
            lastKnownAdConsentStatus = AdConsentStatus.PERSONALIZED_NON_UE
        )
        val publisher = getFlowPublisher { gdprConsent.requestGDPRLocation() }
        splashVMImpl.onStart()
        assertEquals(SplashState.LOADING, splashVMImpl.splashState.value)
        publisher.offer(CheckGDPRLocationStatus.UE)
        assertEquals(SplashState.SHOW_GDPR_CONSENT, splashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM states when noAdsPurchases noAdsActive emitted false first and true later`() = runBlockingTest {
        setUpMocks(
            isNoAdsActive = false,
            lastKnownAdConsentStatus = AdConsentStatus.UNINITIALIZED,
            gdprLocationStatus = CheckGDPRLocationStatus.NON_UE
        )

        val noAdsActivePublisher = getFlowPublisher { noAdsPurchases.noAdsActive }
        splashVMImpl.onStart()
        assertEquals(SplashState.LOADING, splashVMImpl.splashState.value)
        noAdsActivePublisher.offer(true)
        assertEquals(SplashState.FINISHED, splashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM states when noAdsPurchases noAdsActive emitted false first and true later when GDPR IN UE`() = runBlockingTest {
        setUpMocks(
            isNoAdsActive = false,
            lastKnownAdConsentStatus = AdConsentStatus.UNINITIALIZED,
            gdprLocationStatus = CheckGDPRLocationStatus.UE
        )

        val noAdsActivePublisher = getFlowPublisher { noAdsPurchases.noAdsActive }
        splashVMImpl.onStart()
        assertEquals(SplashState.SHOW_GDPR_CONSENT, splashVMImpl.splashState.value)
        noAdsActivePublisher.offer(true)
        assertEquals(SplashState.FINISHED, splashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM has GDPR_POP_UP status when there is first launch with no internet`() {
        setUpMocks(isConnectedOrConnecting = false)
        splashVMImpl.onStart()
        assertEquals(SplashState.SHOW_GDPR_CONSENT, splashVMImpl.splashState.value)
    }

    @Test
    fun `SplashVM isFirstLaunch == true when there is first launch with connected internet`() {
        setUpMocks(isConnectedOrConnecting = true)
        splashVMImpl.onStart()
        assertEquals(true, splashVMImpl.isFirstLaunch.value)
    }

    @Test
    fun `SplashVM finishes and cleans after noAds true called`() = runBlocking {
        setUpMocks(isNoAdsActive = true)
        splashVMImpl.onStart()
        assertEquals(SplashState.FINISHED, splashVMImpl.splashState.value)
        assertEquals(false, splashVMImpl.viewModelScope.isActive)
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
        every { networkStatus.isConnectedOrConnecting }.returns(isConnectedOrConnecting)
    }
}