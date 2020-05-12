package pl.netigen.coreapi.gdpr

/**
 * Configuration for Gdpr Consent
 *
 * @see [GDPRConsentImpl]
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