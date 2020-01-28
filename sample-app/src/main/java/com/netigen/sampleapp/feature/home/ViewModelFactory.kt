package com.netigen.sampleapp.feature.home

import android.app.Application
import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.gms.payments.GMSPayments

class ViewModelFactory(activity: CoreMainActivity) : CoreViewModelsFactory(activity) {
    override val appConfig = AppConfig(
        bannerAdId = "",
        interstitialAdId = "",
        rewardedAdId = "test",
        inDebugMode = true
    )
    override val payments: IPayments
        get() = getPayments(mainActivity.application)

    private fun getPayments(application: Application): IPayments = GMSPayments(application)
}