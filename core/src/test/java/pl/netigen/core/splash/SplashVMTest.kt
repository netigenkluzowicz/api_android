package pl.netigen.core.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
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
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.ISplashTimer
import pl.netigen.coreapi.splash.SplashState


@ExperimentalCoroutinesApi
class SplashVMTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var splashVM: SplashVM
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
    @RelaxedMockK
    private lateinit var splashTimer: ISplashTimer
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        splashVM = SplashVM(gdprConsent, ads, noAdsPurchases, networkStatus, splashTimer, coroutineDispatcherIo = Dispatchers.Main)
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
        assertEquals(SplashState.UNINITIALIZED, splashVM.splashState.value)
    }

    @Test
    fun `SplashVM has FINISHED state after onStart when noAds is active`() = runBlocking {
        setUpMocks(isNoAdsActive = true)
        splashVM.onStart()
        assertEquals(SplashState.FINISHED, splashVM.splashState.value)
    }

    @Test
    fun `SplashVM states when noAdsPurchases noAdsActive emitted false first and true later`() = runBlockingTest {
        setUpMocks(
            isNoAdsActive = false,
            lastKnownAdConsentStatus = AdConsentStatus.UNINITIALIZED,
            gdprLocationStatus = CheckGDPRLocationStatus.NON_UE
        )
        val publisher = ConflatedBroadcastChannel(false)
        every { noAdsPurchases.noAdsActive }.returns(publisher.asFlow())
        splashVM.onStart()
        assertEquals(SplashState.LOADING_FIRST_LAUNCH, splashVM.splashState.value)
        publisher.offer(true)
        assertEquals(SplashState.FINISHED, splashVM.splashState.value)
    }

    private fun setUpMocks(
        isNoAdsActive: Boolean = false,
        lastKnownAdConsentStatus: AdConsentStatus = AdConsentStatus.UNINITIALIZED,
        isConnectedOrConnecting: Boolean = true,
        gdprLocationStatus: CheckGDPRLocationStatus = CheckGDPRLocationStatus.ERROR

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

    @Test
    fun `SplashVM has GDPR_POP_UP status when there is first launch with no internet`() {
        setUpMocks(isConnectedOrConnecting = false)
        splashVM.onStart()
        assertEquals(SplashState.GDPR_POP_UP, splashVM.splashState.value)
    }

    @Test
    fun `SplashVM has LOADING_FIRST_LAUNCH status when there is first launch with connected internet`() {
        setUpMocks(isConnectedOrConnecting = true)
        splashVM.onStart()
        assertEquals(SplashState.LOADING_FIRST_LAUNCH, splashVM.splashState.value)
    }

    @Test
    fun `SplashVM cleans listeners after noAds true called`() = runBlocking {
        setUpMocks(isNoAdsActive = true)
        splashVM.onStart()
        verify { networkStatus.removeNetworkStatusChangeListener(any()) }
    }
}