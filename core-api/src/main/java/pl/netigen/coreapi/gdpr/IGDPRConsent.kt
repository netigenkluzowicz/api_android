package pl.netigen.coreapi.gdpr

import kotlinx.coroutines.flow.Flow

interface IGDPRConsent : IGDPRTexts {
    val adConsentStatus: Flow<AdConsentStatus>

    fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus>
    fun saveAdConsentStatus(adConsentStatus: AdConsentStatus)
}

interface IGDPRTexts {
    val textPolicy1: String
    val textPolicy2: String
    val text1: String
    val text2: String
    val text3: String
    val text4: String
    val text5: String
}