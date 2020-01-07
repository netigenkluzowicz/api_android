package pl.netigen.coreapi.gdpr

interface IGDPRConsent {
    val lastKnownAdConsentStatus: AdConsentStatus
    fun requestGDPRLocation(gdprLocationStatusListener: (result: CheckGDPRLocationStatus) -> Unit)
    fun saveAdConsentStatus(adConsentStatus: AdConsentStatus)
    fun cancelRequest()
    fun isConsentShowed() =
        (lastKnownAdConsentStatus == AdConsentStatus.NON_PERSONALIZED_SHOWED || lastKnownAdConsentStatus == AdConsentStatus.PERSONALIZED_SHOWED)
}