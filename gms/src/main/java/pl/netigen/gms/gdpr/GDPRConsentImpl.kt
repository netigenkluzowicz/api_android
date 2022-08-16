package pl.netigen.gms.gdpr

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import com.google.android.ump.*
import com.google.android.ump.ConsentInformation.ConsentStatus.REQUIRED
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


    override fun requestGDPRLocation(onGdprStatus: (CheckGDPRLocationStatus) -> Unit) {
        Timber.d("checkGDPRLocationStatus = [$onGdprStatus]")

        val consentInformation = UserMessagingPlatform.getConsentInformation(activity)

        val params = ConsentRequestParameters.Builder()
            .build();


        val callback = object : ConsentInformation.OnConsentInfoUpdateFailureListener, ConsentInformation.OnConsentInfoUpdateSuccessListener {

            override fun onConsentInfoUpdateFailure(formError: FormError?) {
                if (formError != null) {
                    Timber.d("p0 = [${formError.message}]")
                }
                onGdprStatus(CheckGDPRLocationStatus.ERROR)

            }

            override fun onConsentInfoUpdateSuccess() {
                val consentStatus = consentInformation.consentStatus
                val isInEea = consentStatus == REQUIRED
                if (isInEea && consentInformation.isConsentFormAvailable) {
                    onGdprStatus(CheckGDPRLocationStatus.UE)
                } else if (isInEea) {
                    onGdprStatus(CheckGDPRLocationStatus.ERROR)
                } else {
                    onGdprStatus(CheckGDPRLocationStatus.NON_UE)
                }
            }
        }

        consentInformation.requestConsentInfoUpdate(activity, params, callback, callback)
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
            consentForm1.show(activity) { gdprResult(adConsentStatus()) }
        } else {
            gdprResult(AdConsentStatus.NON_PERSONALIZED_ERROR)
        }
    }

    private fun adConsentStatus() = when (UserMessagingPlatform.getConsentInformation(activity).consentStatus) {
        ConsentInformation.ConsentStatus.UNKNOWN -> AdConsentStatus.NON_PERSONALIZED_ERROR
        ConsentInformation.ConsentStatus.NOT_REQUIRED -> AdConsentStatus.NON_PERSONALIZED_SHOWED
        ConsentInformation.ConsentStatus.OBTAINED -> AdConsentStatus.PERSONALIZED_SHOWED
        else -> AdConsentStatus.NON_PERSONALIZED_ERROR
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
