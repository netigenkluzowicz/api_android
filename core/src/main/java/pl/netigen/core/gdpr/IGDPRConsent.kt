package pl.netigen.core.gdpr

interface IGDPRConsent {
    val lastKnownAdConsentStatus: AdConsentStatus

    fun checkGDPRLocation(gdprLocationStatusListener: (result: CheckGDPRLocationStatus) -> Unit)
    fun saveAdConsentStatus(adConsentStatus: AdConsentStatus)

}