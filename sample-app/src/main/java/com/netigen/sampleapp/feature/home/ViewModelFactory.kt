package com.netigen.sampleapp.feature.home

import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.gms.payments.GMSPayments

class ViewModelFactory(coreMainActivity: CoreMainActivity) : CoreViewModelsFactory(coreMainActivity) {
    override val appConfig by lazy {
        AppConfig(
            bannerAdId = "",
            interstitialAdId = "",
            rewardedAdId = "test",
            inDebugMode = true
        )
    }
    override val payments: IPayments
        get() = GMSPayments(activity.application)

}