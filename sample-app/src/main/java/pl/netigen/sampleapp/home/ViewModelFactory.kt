@file:Suppress("KotlinConstantConditions")

package pl.netigen.sampleapp.home

import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.Store
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.sampleapp.BuildConfig
import pl.netigen.sampleapp.flavour.FlavoursConst

class ViewModelFactory(override val coreMainActivity: CoreMainActivity) : CoreViewModelsFactory(coreMainActivity) {

    override val appConfig by lazy {
        AppConfig(
            bannerAdId = FlavoursConst.BANNER_AD_ID,
            bannerYandexAdId = FlavoursConst.YANDEX_BANNER_AD_ID,
            interstitialAdId = FlavoursConst.INTERSTITIAL_AD_ID,
            interstitialYandexAdId = FlavoursConst.YANDEX_INTERSTITIAL_AD_ID,
            rewardedAdId = FlavoursConst.REWARDED_AD_ID,
            rewardedYandexAdId = FlavoursConst.YANDEX_REWARDED_AD_ID,
            openAppAdId = "ca-app-pub-3940256099942544/9257395921",
            inDebugMode = BuildConfig.DEBUG,
            inYandexDebugMode = false,
            store = when (BuildConfig.FLAVOR) {
                "amazon" -> Store.AMAZON
                "gms" -> Store.GOOGLE_PLAY
                else -> Store.GOOGLE_PLAY
            },
        )
    }

    override val ads: IAds
        get() = FlavoursConst.getAdsImpl(coreMainActivity, appConfig)

    override val gdprConsent: IGDPRConsent
        get() = FlavoursConst.getGDPRConsentImpl(coreMainActivity)

    override val payments: IPayments
        get() = FlavoursConst.getPaymentsImpl(coreMainActivity, appConfig)
}
