package pl.netigen.coreapi.gdpr

/**
 * Possible user GDPR location statuses:
 * - [UE] Consent should be displayed
 * - [NON_UE] Consent can be skipped
 * - [ERROR] Consent should be displayed because location is unknown
 *
 */
enum class CheckGDPRLocationStatus {
    UE, NON_UE, ERROR
}