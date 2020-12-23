package pl.netigen.gms.gdpr

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import com.google.android.ump.*
import com.google.android.ump.ConsentInformation.ConsentStatus.REQUIRED
import com.google.android.ump.ConsentInformation.ConsentStatus.UNKNOWN
import com.google.android.ump.ConsentInformation.ConsentType.NON_PERSONALIZED
import com.google.android.ump.ConsentInformation.ConsentType.PERSONALIZED
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import pl.netigen.coreapi.gdpr.*
import timber.log.Timber


/**
 * [IGDPRConsent] implementation with using [User Messaging Platform](https://developers.google.com/admob/ump/android/quick-start)
 *
 * @property activity [Activity] context for this implementation
 */
class GDPRConsentImpl(private val activity: ComponentActivity) : IGDPRConsent, IGDPRTexts by ConstGDPR {
    var consentForm: ConsentForm? = null;


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
                            val consentStatus = consentInformation.consentStatus
                            val isInEea = consentStatus == REQUIRED
                            if (consentInformation.isConsentFormAvailable) {
                                UserMessagingPlatform.loadConsentForm(
                                        activity,
                                        { form ->
                                            consentForm = form
                                            if (consentStatus == REQUIRED) {
                                                consentForm?.show(activity) {
                                                    if (it != null) {
                                                        sendBlocking(CheckGDPRLocationStatus.FORM_SHOWED)
                                                        saveAdConsentStatus(consentInformation.consentType)
                                                    } else {
                                                        sendBlocking(CheckGDPRLocationStatus.ERROR)
                                                    }
                                                }
                                            } else {
                                                saveAdConsentStatus(AdConsentStatus.PERSONALIZED_NON_UE)
                                                sendBlocking(CheckGDPRLocationStatus.NON_UE)
                                            }
                                        },
                                        {
                                            sendBlocking(if (isInEea) CheckGDPRLocationStatus.UE else CheckGDPRLocationStatus.NON_UE)
                                        }
                                )
                            }


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

    private fun saveAdConsentStatus(adConsentStatus: Int) {
        when (adConsentStatus) {
            UNKNOWN -> saveAdConsentStatus(AdConsentStatus.UNINITIALIZED)
            NON_PERSONALIZED -> saveAdConsentStatus(AdConsentStatus.NON_PERSONALIZED_SHOWED)
            PERSONALIZED -> saveAdConsentStatus(AdConsentStatus.PERSONALIZED_SHOWED)
        }
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