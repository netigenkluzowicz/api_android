package pl.netigen.coreapi.main

import android.app.Activity
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.SingleLiveEvent

/**
 * This implementations, provides access to api modules:
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
}
