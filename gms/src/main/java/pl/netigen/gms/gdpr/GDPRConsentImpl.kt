package pl.netigen.gms.gdpr

import android.app.Application
import android.content.Context
import androidx.activity.ComponentActivity
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentInformation.ConsentStatus.REQUIRED
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.FormError
import com.google.android.ump.UserMessagingPlatform
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
 * @property activity [Application] context for this implementation
 * @property config [IGDPRConsentConfig] with list of Admob publisher IDs
 */
class GDPRConsentImpl(private val activity: ComponentActivity, private val config: IGDPRConsentConfig) : IGDPRConsent, IGDPRTexts by ConstGDPR {

    override val adConsentStatus: Flow<AdConsentStatus> = flow {
        val value =
                activity.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getInt(
                        PREFERENCES_KEY, AdConsentStatus.UNINITIALIZED.ordinal
                )
        emit(AdConsentStatus.values().getOrElse(value) { AdConsentStatus.UNINITIALIZED })
    }

    override fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus> =
            callbackFlow {
                val consentInformation = UserMessagingPlatform.getConsentInformation(activity);
                val callback = object : ConsentInformation.OnConsentInfoUpdateFailureListener, ConsentInformation.OnConsentInfoUpdateSuccessListener {

                    override fun onConsentInfoUpdateFailure(p0: FormError?) {
                        try {
                            sendBlocking(CheckGDPRLocationStatus.ERROR)
                        } catch (e: Exception) {
                            Timber.e(e)
                        } finally {
                            channel.close()
                        }
                    }

                    override fun onConsentInfoUpdateSuccess() {
                        try {
                            val isInEea = consentInformation.consentStatus == REQUIRED
                            sendBlocking(if (isInEea) CheckGDPRLocationStatus.UE else CheckGDPRLocationStatus.NON_UE)
                        } catch (e: Exception) {
                            Timber.e(e)
                        } finally {
                            channel.close()
                        }
                    }
                }
                val params = ConsentRequestParameters.Builder()
                        .setTagForUnderAgeOfConsent(false)
                        .build();
                consentInformation.requestConsentInfoUpdate(activity, params, callback, callback)

                awaitClose {}
            }

    override fun saveAdConsentStatus(adConsentStatus: AdConsentStatus) {
        activity.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt(PREFERENCES_KEY, adConsentStatus.ordinal)
                .apply()
    }

    companion object {
        const val PREFERENCES_NAME = "GDPRConsent"
        const val PREFERENCES_KEY = "AdConsentStatus"
    }
}