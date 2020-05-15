package pl.netigen.coreapi.gdpr

/**
 * Configurations for Gdpr Consent, as for now only List of Admob publisher ids is needed
 *
 */
interface IGDPRConsentConfig {

    /**
     * List of Admob publisher ids
     *
     * **See Also:** [More information][https://developers.google.com/admob/android/eu-consent]
     */
    val adMobPublisherIds: Array<String>
}