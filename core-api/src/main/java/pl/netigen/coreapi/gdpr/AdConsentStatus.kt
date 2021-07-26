package pl.netigen.coreapi.gdpr

/**
 * Possible values of Gdpr ads consent status
 *
 * - [PERSONALIZED_NON_UE] - user location allows use of personalized ads
 * - [PERSONALIZED_SHOWED] - consent was collected from user with personalized ads allowed
 * - [NON_PERSONALIZED_SHOWED] - consent was collected from user with personalized ads not allowed
 * - [UNINITIALIZED] - consent was not collected and user location is not checked - ads should not be displayed
 *
 */
enum class AdConsentStatus {
    PERSONALIZED_NON_UE, PERSONALIZED_SHOWED, NON_PERSONALIZED_SHOWED, UNINITIALIZED
}