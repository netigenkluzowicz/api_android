package pl.netigen.coreapi.main

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments

interface ICoreViewModelsFactory : ViewModelProvider.Factory {
    val activity: Activity
    val appConfig: IAppConfig
    val networkStatus: INetworkStatus
    val admob: IAds
    val gdprConsent: IGDPRConsent
    val payments: IPayments
}