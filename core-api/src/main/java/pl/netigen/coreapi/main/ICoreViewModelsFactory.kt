package pl.netigen.coreapi.main

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments

/**
 * Interface used for provide [ViewModelProvider.Factory] to instantiate [ICoreMainVM] implementation
 *
 * see:  [ICoreMainVM]
 */
interface ICoreViewModelsFactory : ViewModelProvider.Factory {
    val coreMainActivity: ICoreMainActivity
    val appConfig: IAppConfig
    val networkStatus: INetworkStatus
    val ads: IAds
    val gdprConsent: IGDPRConsent
    val payments: IPayments
}