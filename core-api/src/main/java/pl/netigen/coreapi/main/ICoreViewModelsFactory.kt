package pl.netigen.coreapi.main

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.coreapi.splash.SplashVM

interface ICoreViewModelsFactory : ViewModelProvider.Factory {
    val mainActivity: Activity
    val appConfig: IAppConfig
    val payments: IPayments
    val ads: IAds
    val networkStatus: INetworkStatus
    fun getSplashVm(): SplashVM
    fun getCoreMainVm(): CoreMainVM
}