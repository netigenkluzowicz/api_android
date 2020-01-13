package pl.netigen.core.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.purchases.INoAdsPurchases
import pl.netigen.coreapi.purchases.NoAdsPurchaseListener
import pl.netigen.coreapi.splash.ISplashTimer
import pl.netigen.coreapi.splash.SplashState


class SplashVMTest() {
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
    private lateinit var noAdsPurchases: INoAdsPurchases
    @RelaxedMockK
    private lateinit var networkStatus: INetworkStatus
    @RelaxedMockK
    private lateinit var splashTimer: ISplashTimer

    @Before
    fun before() {
        MockKAnnotations.init(this)
        splashVM = SplashVM(gdprConsent, ads, noAdsPurchases, networkStatus, splashTimer)
        every { ads.interstitialAd } returns interstitialAd
    }

    @Test
    fun `SplashVM has UNINITIALIZED state after create`() {
        assertEquals(SplashState.UNINITIALIZED, splashVM.splashState.value)
    }

    @Test
    fun `SplashVM has FINISHED state after onStart when noAds is active`() {
        setUpMocks(isNoAdsActive = true)
        splashVM.onStart()
        assertEquals(SplashState.FINISHED, splashVM.splashState.value)
    }

    @Test
    fun `SplashVM has FINISHED state after onStart when noAds callback is called`() {
        setUpMocks()
        val slot = slot<NoAdsPurchaseListener>()
        every { noAdsPurchases.addNoAdsPurchaseListener(capture(slot)) } just runs
        splashVM.onStart()
        assertEquals(SplashState.LOADING_FIRST_LAUNCH, splashVM.splashState.value)
        slot.captured.onNoAdsPurchaseChanged(true)
        assertEquals(SplashState.FINISHED, splashVM.splashState.value)
    }

    private fun setUpMocks(
        isNoAdsActive: Boolean = false,
        lastKnownAdConsentStatus: AdConsentStatus = AdConsentStatus.UNINITIALIZED,
        isConnectedOrConnecting: Boolean = true
    ) {
        every { noAdsPurchases.isNoAdsActive() }.returns(isNoAdsActive)
        every { gdprConsent.lastKnownAdConsentStatus }.returns(lastKnownAdConsentStatus)
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
    fun `SplashVM cleans listeners after finish called`() {
        setUpMocks(isNoAdsActive = true)
        splashVM.onStart()
        assertEquals(SplashState.FINISHED, splashVM.splashState.value)
        verify { noAdsPurchases.removeNoAdsPurchaseListener(splashVM) }
    }
}