package pl.netigen.gms.gdpr

import android.app.Activity
import android.content.Context
import android.util.Log
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
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.gdpr.IGDPRTexts
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
            Timber.d("activity %s", activity)
            val callback = object : ConsentInformation.OnConsentInfoUpdateFailureListener, ConsentInformation.OnConsentInfoUpdateSuccessListener {

                override fun onConsentInfoUpdateFailure(formError: FormError?) {
                    if (formError != null) {
                        Timber.d("p0 = [${formError.message}]")
                    }
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
                        if (isInEea && consentInformation.isConsentFormAvailable) {
                            sendBlocking(CheckGDPRLocationStatus.FORM_SHOW_REQUIRED)
                        } else if (isInEea) {
                            sendBlocking(CheckGDPRLocationStatus.ERROR)
                        } else {
                            sendBlocking(CheckGDPRLocationStatus.NON_UE)
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

    override fun loadGdpr(onLoadSuccess: (Boolean) -> Unit) {
        val callback = object : UserMessagingPlatform.OnConsentFormLoadSuccessListener,
            UserMessagingPlatform.OnConsentFormLoadFailureListener {
            override fun onConsentFormLoadSuccess(form: ConsentForm?) {
                Timber.d("form = [$form]")
                consentForm = form
                if (form != null) {
                    onLoadSuccess(true)
                } else {
                    onLoadSuccess(false)
                }
            }

            override fun onConsentFormLoadFailure(formError: FormError?) {
                if (formError != null) {
                    Timber.d("formError = [${formError.message}]")
                }
                onLoadSuccess(false)
            }
        }
        UserMessagingPlatform.loadConsentForm(activity, callback, callback)
    }

    override fun showGdpr(gdprResult: (AdConsentStatus) -> Unit) {
        Timber.d("gdprResult = [$gdprResult]")
        val consentForm1 = consentForm
        if (consentForm1 != null) {
            consentForm1.show(activity) {

            }
        } else {
            sendBlocking(false)
            channel.close()
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