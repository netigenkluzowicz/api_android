package pl.netigen.coreapi.main

import androidx.lifecycle.ViewModelProvider
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.coreapi.splash.ISplashVM

/**
 * Interface used for provide [ViewModelProvider.Factory] to instantiate [ICoreMainVM] and [ISplashVM] implementations
 *
 */
interface ICoreViewModelsFactory : ViewModelProvider.Factory {
    /**
     * Main api activity provides context for Api modules
     */
    val coreMainActivity: ICoreMainActivity

    /**
     * Provides [IAppConfig] to Api view models
     */
    val appConfig: IAppConfig

    /**
     * Provides [INetworkStatus] to Api view models
     */
    val networkStatus: INetworkStatus

    /**
     * Provides [IAds] to Api view models
     */
    val ads: IAds

    /**
     * Provides [IGDPRConsent] to Api view models
     */
    val gdprConsent: IGDPRConsent

    /**
     * Provides [IPayments] to Api view models
     */
    val payments: IPayments
}