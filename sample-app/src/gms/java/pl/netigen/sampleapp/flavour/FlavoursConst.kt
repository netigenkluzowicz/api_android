package pl.netigen.sampleapp.flavour

import android.app.Application
import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.gms.ads.AdMobAds
import pl.netigen.gms.ads.AdMobAds.Companion.TEST_REWARDED_ID
import pl.netigen.gms.gdpr.GDPRConsentImpl
import pl.netigen.gms.payments.GMSPayments

object FlavoursConst {
    const val BANNER_AD_ID: String = ""
    const val INTERSTITIAL_AD_ID: String = ""
    const val REWARDED_AD_ID: String = TEST_REWARDED_ID

    fun getPaymentsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig): IPayments =
        GMSPayments(coreMainActivity, appConfig.inDebugMode)

    fun getAdsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig) = AdMobAds(coreMainActivity, appConfig)
    fun getGDPRConsentImpl(application: Application, appConfig: AppConfig) = GDPRConsentImpl(application, appConfig)
}