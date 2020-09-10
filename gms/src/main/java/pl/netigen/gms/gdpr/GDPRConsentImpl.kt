package pl.netigen.gms.gdpr

import android.app.Application
import android.content.Context
import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import pl.netigen.coreapi.gdpr.*
import timber.log.Timber

/**
 * [IGDPRConsent] implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)
 *
 * @property application [Application] context for this implementation
 * @property config [IGDPRConsentConfig] with list of Admob publisher IDs
 */
class GDPRConsentImpl(private val application: Application, private val config: IGDPRConsentConfig) : IGDPRConsent, IGDPRTexts by ConstGDPR {
    val consentInformation: ConsentInformation = ConsentInformation.getInstance(application)
    override val adConsentStatus: Flow<AdConsentStatus> = flow {
        val value =
            application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getInt(
                PREFERENCES_KEY, AdConsentStatus.UNINITIALIZED.ordinal
            )
        emit(AdConsentStatus.values().getOrElse(value) { AdConsentStatus.UNINITIALIZED })
    }

    override fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus> =
        callbackFlow {
            val callback = object : ConsentInfoUpdateListener {
                override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                    try {
                        val isInEea = consentInformation.isRequestLocationInEeaOrUnknown
                        sendBlocking(if (isInEea) CheckGDPRLocationStatus.UE else CheckGDPRLocationStatus.NON_UE)
                    } catch (e: Exception) {
                        Timber.e(e)
                    } finally {
                        channel.close()
                    }
                }

                override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                    try {
                        sendBlocking(CheckGDPRLocationStatus.ERROR)
                    } catch (e: Exception) {
                        Timber.e(e)
                    } finally {
                        channel.close()
                    }
                }
            }
            consentInformation.requestConsentInfoUpdate(config.adMobPublisherIds, callback)
            awaitClose {}
        }

    override fun saveAdConsentStatus(adConsentStatus: AdConsentStatus) {
        application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .putInt(PREFERENCES_KEY, adConsentStatus.ordinal)
            .apply()
    }

    companion object {
        const val PREFERENCES_NAME = "GDPRConsent"
        const val PREFERENCES_KEY = "AdConsentStatus"
    }
}