package pl.netigen.coreapi.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
abstract class SplashVM(application: Application) : AndroidViewModel(application), ISplashVM