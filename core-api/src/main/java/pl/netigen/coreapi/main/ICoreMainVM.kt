package pl.netigen.coreapi.main

import android.app.Activity
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.SingleLiveEvent

/**
 * Used for core view model for [ICoreMainActivity] implementation in this Api, provides access to:
 * - payments [IPayments]
 * - ads [IAds]
 * - network status [INetworkStatus]
 * - GDPR consent [IGDPRConsent]
 * - api configuration [IAppConfig]
 *
 */
interface ICoreMainVM : IPayments, IAds, INetworkStatus, IGDPRConsent, IAppConfig {
    /**
     * It is called on [onStart()][Activity.onStart] of [ICoreMainActivity] implementation
     *
     */
    fun start()

    /**
     * Shows [GDPRDialogFragment][pl.netigen.core.gdpr.GDPRDialogFragment] in [ICoreMainActivity] implementation to reset user ads Consent
     *
     * see: [IGDPRConsent]
     *
     */
    fun resetAdsPreferences()

    /**
     * [ICoreMainActivity] will observe it and show [GDPRDialogFragment][pl.netigen.core.gdpr.GDPRDialogFragment] when [resetAdsPreferences] is called
     *
     */
    val showGdprResetAds: SingleLiveEvent<Unit>

    /**
     * Indicating that no ads in-app purchase is active or inactive
     *
     * see: [INoAds.noAdsActive]
     */
    val currentIsNoAdsActive: Boolean

}