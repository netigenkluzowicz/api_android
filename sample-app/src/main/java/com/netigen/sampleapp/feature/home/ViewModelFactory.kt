package com.netigen.sampleapp.feature.home

import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.gms.payments.GMSPayments

class ViewModelFactory(activity: CoreMainActivity) : CoreViewModelsFactory(activity) {
    override val appConfig = AppConfig(
        bannerAdId = "",
        interstitialAdId = "",
        inDebugMode = true
    )

    override val payments: GMSPayments = GMSPayments(activity.application)
}