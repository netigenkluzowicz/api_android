package pl.netigen.coreapi.gdpr

import kotlinx.coroutines.flow.Flow

/**
 * Its used for collect user consent for displaying ads, and ask them if we can provide personalized ads
 *
 * Ads policies to your users in the European Economic Area (EEA)
 * and the UK and obtain their consent for the use of cookies or other local storage where legally required,
 * and for the collection, sharing, and use of personal data for ads personalization.
 * This policies reflects the requirements of the EU ePrivacy Directive and the General Data Protection Regulation (GDPR).
 *
 * [More info Admob](https://github.com/googleads/googleads-consent-sdk-android)
 *
 * [More info HMS Ads Kit](https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/ads-sdk-settings#h1-1585566951865)
 *
 */
interface IGDPRConsent : IGDPRTexts {

    /**
     * Saves current consent status:
     * - [AdConsentStatus.PERSONALIZED_NON_UE] - user location allows use of personalized ads
     * - [AdConsentStatus.PERSONALIZED_SHOWED] - consent was collected from user with personalized ads allowed
     * - [AdConsentStatus.NON_PERSONALIZED_SHOWED] - consent was collected from user with personalized ads not allowed
     * - [AdConsentStatus.UNINITIALIZED] - consent was not collected and user location is not checked- ads should not be displayed
     *
     * @param adConsentStatus Collected consent information to save
     */
    fun saveAdConsentStatus(adConsentStatus: AdConsentStatus)

    /**
     * Request check if user is located in place where we should display consent to him
     *
     * @return Location Status:
     * - [CheckGDPRLocationStatus.UE] - Consent should be displayed
     * - [CheckGDPRLocationStatus.NON_UE] - Consent can be skipped
     * - [CheckGDPRLocationStatus.ERROR] - Consent should be displayed because location is unknown
     *
     */
    fun requestGDPRLocation(checkGDPRLocationStatus: CheckGDPRLocationStatus)

    /**
     * Loads gdpr popup/form and provides load success/failure callback
     *
     * @param onLoadSuccess Callback called when form is loaded or an error occurred
     */
    fun loadGdpr(onLoadSuccess: (Boolean) -> Unit)

    /**
     * Shows gdpr popup/form and passes resulted consent status from user
     *
     * @param gdprResult Callback provides AdConsentStatus from user
     */
    fun showGdpr(gdprResult: (AdConsentStatus) -> Unit)
}

/**
 * List of texts for Consent PopUp
 * See implementations for more info
 *
 */
interface IGDPRTexts {
    val textPolicy1: String
    val textPolicy2: String
    val text1: String
    val text2: String
    val text3: String
    val text4: String
    val text5: String
}