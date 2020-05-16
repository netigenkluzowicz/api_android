package pl.netigen.coreapi.gdpr

/**
 * Configurations for Gdpr Consent, as for now only List of Admob publisher ids is needed
 *
 */
interface IGDPRConsentConfig {

    /**
     * List of Admob publisher ids
     *
     * **See Also:** [Mobile Ads SDK (Android) - Update consent status](https://developers.google.com/admob/android/eu-consent#update-status)
     */
    val adMobPublisherIds: Array<String>
}