package pl.netigen.coreapi.main

import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.SingleLiveEvent

interface ICoreMainVM : IPayments, IAds, INetworkStatus, IGDPRConsent, IAppConfig {
    fun start()
    fun resetAdsPreferences()
    val showGdprResetAds : SingleLiveEvent<Unit>
    val currentIsNoAdsActive : Boolean
}