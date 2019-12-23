package pl.netigen.core.netigenapi

interface IConsent {
    val lastKnownAdConsentStatus: AdConsentStatus
    val onConsentResult: (AdConsentStatus) -> Unit

    enum class AdConsentStatus {
        PERSONALIZED_NON_UE, PERSONALIZED_SHOWED, NON_PERSONALIZED_SHOWED, UNINITIALIZED
    }
}