package pl.netigen.coreapi.main

import androidx.lifecycle.ViewModelProvider
import pl.netigen.core.ads.AdMobAds
import pl.netigen.core.config.AppConfig
import pl.netigen.core.network.NetworkStatus
import pl.netigen.coreapi.payments.IPayments

interface ICoreViewModelsFactory : ViewModelProvider.Factory {
    val coreMainActivity: CoreMainActivity
    val appConfig: AppConfig
    val payments: IPayments
    val ads: AdMobAds
    val networkStatus: NetworkStatus
}