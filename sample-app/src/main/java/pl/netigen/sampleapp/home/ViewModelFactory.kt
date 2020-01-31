package pl.netigen.sampleapp.home

import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.sampleapp.flavour.FlavoursConst

class ViewModelFactory(private val coreMainActivity: CoreMainActivity) : CoreViewModelsFactory(coreMainActivity) {
    override val appConfig by lazy {
        AppConfig(
            bannerAdId = "",
            interstitialAdId = "",
            rewardedAdId = "test",
            inDebugMode = true
        )
    }
    override val payments: IPayments
        get() = FlavoursConst.getPaymentsImpl(coreMainActivity)

}