package pl.netigen.coreapi.gdpr

import kotlinx.coroutines.flow.Flow

interface IGDPRConsent {
    val adConsentStatus: Flow<AdConsentStatus>
    fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus>
    fun saveAdConsentStatus(adConsentStatus: AdConsentStatus)
}