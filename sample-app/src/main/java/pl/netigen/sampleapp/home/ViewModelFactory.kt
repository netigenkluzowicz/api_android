package pl.netigen.sampleapp.home

import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.sampleapp.flavour.FlavoursConst

class ViewModelFactory(private val coreMainActivity: CoreMainActivity) : CoreViewModelsFactory(coreMainActivity) {
    override val appConfig by lazy {
        AppConfig(
            bannerAdId = FlavoursConst.bannerAdId,
            interstitialAdId = FlavoursConst.interstitialAdId,
            rewardedAdId = FlavoursConst.rewardedAdId,
            inDebugMode = true
        )
    }
    override val ads: IAds by lazy { FlavoursConst.getAdsImpl(coreMainActivity, appConfig) }

    override val gdprConsent: IGDPRConsent by lazy { FlavoursConst.getGDPRConsentImpl(coreMainActivity.application, appConfig) }

    override val payments: IPayments by lazy { FlavoursConst.getPaymentsImpl(coreMainActivity) }

}