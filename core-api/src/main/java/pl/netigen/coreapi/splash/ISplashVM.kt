package pl.netigen.coreapi.splash

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.payments.INoAds

/**
 *  Its used for Splash [AndroidViewModel], provides application initialization,:
 *  - billing if available and no-ads payments, see [INoAds]
 *  - app configuration by [IAppConfig] implementation,
 *  - Gdpr Consent for ads and shows pop up to the users if necessary, see: [IGDPRConsent]
 *
 *  After initialization:
 *  - if ads are active - loads [IInterstitialAd], and show it to the user, and after closes Splash
 *  - if ads are disabled, closes Splash
 *
 */
interface ISplashVM : INoAds, IAppConfig {
    val isPremium : Boolean
    val gdprConsent: IGDPRConsent
    val splashState: LiveData<SplashState>
    val isFirstLaunch: LiveData<Boolean>
    fun setPersonalizedAds(personalizedAdsApproved: Boolean)
    fun start()
}
