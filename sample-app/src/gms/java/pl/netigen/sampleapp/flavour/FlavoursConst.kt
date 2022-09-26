package pl.netigen.sampleapp.flavour

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

    const val NO_ADS_KEY = ".noads"
    const val SUBSCRIPTION_12 = ".subs_12_not_test"
    const val SUBSCRIPTION_1 = ".subs_01"
    const val NO_ADS_INFO_KEY = ".noads_info"

    fun getPaymentsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig): IPayments {
        val inAppSkuList = mutableListOf(
            coreMainActivity.packageName + NO_ADS_KEY,
            coreMainActivity.packageName + NO_ADS_INFO_KEY,
            coreMainActivity.packageName + SUBSCRIPTION_1,
            coreMainActivity.packageName + SUBSCRIPTION_12,
        )
        val inAppSkuListNoAds = listOf(
            coreMainActivity.packageName + NO_ADS_KEY,
            coreMainActivity.packageName + NO_ADS_INFO_KEY,
            coreMainActivity.packageName + SUBSCRIPTION_1,
            coreMainActivity.packageName + SUBSCRIPTION_12,
        )
        val subscriptionsSkuList = listOf(
            coreMainActivity.packageName + SUBSCRIPTION_1,
            coreMainActivity.packageName + SUBSCRIPTION_12,
        )
        return GMSPayments(
            activity = coreMainActivity,
            inAppSkuList = inAppSkuList,
            noAdsInAppSkuList = inAppSkuListNoAds,
            subscriptionsSkuList = subscriptionsSkuList,
        )
    }

    fun getAdsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig) = AdMobAds(coreMainActivity, appConfig)
    fun getGDPRConsentImpl(coreMainActivity: CoreMainActivity) = GDPRConsentImpl(coreMainActivity)
}
