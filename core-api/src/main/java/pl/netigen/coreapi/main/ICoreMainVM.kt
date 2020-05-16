package pl.netigen.coreapi.main

import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.SingleLiveEvent

/**
 * Used for core view model for main Activity this Api, provides access to:
 * - payments [IPayments]
 * - ads [IAds]
 * - network status [INetworkStatus]
 * - GDPR consent [IGDPRConsent]
 * - api configuration [IAppConfig]
 *
 */
interface ICoreMainVM : IPayments, IAds, INetworkStatus, IGDPRConsent, IAppConfig {
    /**
     * Should be called on Activity start
     *
     */
    fun start()

    /**
     * Shows GDPR pop up to reset user ads Consent
     *
     * see: [IGDPRConsent]
     *
     */
    fun resetAdsPreferences()

    /**
     * [ICoreMainActivity] will observe it and show [pl.netigen.core.gdpr.GDPRDialogFragment] when [resetAdsPreferences] is called
     *
     */
    val showGdprResetAds: SingleLiveEvent<Unit>

    val currentIsNoAdsActive: Boolean
}