package pl.netigen.core.gdpr

interface IGDPRConsent {
    val lastKnownAdConsentStatus: AdConsentStatus
    fun checkGDPRLocation(gdprLocationStatusListener: (result: CheckGDPRLocationStatus) -> Unit)
    fun saveAdConsentStatus(adConsentStatus: AdConsentStatus)
    fun cancelLocating()
    fun isConsentShowed() =
        (lastKnownAdConsentStatus == AdConsentStatus.NON_PERSONALIZED_SHOWED || lastKnownAdConsentStatus == AdConsentStatus.PERSONALIZED_SHOWED)
}