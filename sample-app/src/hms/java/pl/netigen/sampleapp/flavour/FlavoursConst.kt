package pl.netigen.sampleapp.flavour

import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.hms.ads.HMSAds
import pl.netigen.hms.ads.HMSAds.Companion.TEST_REWARDED_ID
import pl.netigen.hms.gdpr.GDPRConsentImpl
import pl.netigen.hms.payments.HMSPayments

object FlavoursConst {
    const val BANNER_AD_ID: String = ""
    const val INTERSTITIAL_AD_ID: String = ""
    const val REWARDED_AD_ID: String = TEST_REWARDED_ID

    @Suppress("UNUSED_PARAMETER")
    fun getPaymentsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig): IPayments = HMSPayments(coreMainActivity)
    fun getAdsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig) = HMSAds(coreMainActivity, appConfig)
    fun getGDPRConsentImpl(coreMainActivity: CoreMainActivity) = GDPRConsentImpl(coreMainActivity.application)
}
