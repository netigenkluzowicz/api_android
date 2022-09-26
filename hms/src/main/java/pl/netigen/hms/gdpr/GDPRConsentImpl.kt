package pl.netigen.hms.gdpr

import android.app.Application
import android.content.Context
import com.huawei.hms.ads.consent.bean.AdProvider
import com.huawei.hms.ads.consent.constant.ConsentStatus
import com.huawei.hms.ads.consent.inter.Consent
import com.huawei.hms.ads.consent.inter.ConsentUpdateListener
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.gdpr.IGDPRTexts
import timber.log.Timber

class GDPRConsentImpl(private val application: Application) : IGDPRConsent, IGDPRTexts by ConstGDPR {
    override fun saveAdConsentStatus(adConsentStatus: AdConsentStatus) {
        Timber.d("adConsentStatus = [$adConsentStatus]")
        val personalized = when (adConsentStatus) {
            AdConsentStatus.PERSONALIZED_NON_UE -> ConsentStatus.PERSONALIZED
            AdConsentStatus.PERSONALIZED_SHOWED -> ConsentStatus.PERSONALIZED
            AdConsentStatus.NON_PERSONALIZED_SHOWED -> ConsentStatus.NON_PERSONALIZED
            AdConsentStatus.UNINITIALIZED -> ConsentStatus.UNKNOWN
            AdConsentStatus.NON_PERSONALIZED_ERROR -> ConsentStatus.NON_PERSONALIZED
        }
        Consent.getInstance(application).setConsentStatus(personalized)
        application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .putInt(PREFERENCES_KEY, adConsentStatus.ordinal)
            .apply()
    }

    override fun requestGDPRLocation(onGdprStatus: (CheckGDPRLocationStatus) -> Unit) {
        val callback = object : ConsentUpdateListener {
            override fun onSuccess(consentStatus: ConsentStatus?, isNeedConsent: Boolean, adProviders: MutableList<AdProvider>?) {
                Timber.d("consentStatus = [$consentStatus], isNeedConsent = [$isNeedConsent], adProviders = [$adProviders]")
                onGdprStatus(if (isNeedConsent) CheckGDPRLocationStatus.UE else CheckGDPRLocationStatus.NON_UE)
            }

            override fun onFail(errorDescription: String?) {
                Timber.d("errorDescription = [$errorDescription]")
                onGdprStatus(CheckGDPRLocationStatus.ERROR)
            }
        }
        Consent.getInstance(application).requestConsentUpdate(callback)
    }

    // todo show gdpr popup
    override fun loadGdpr(onLoadSuccess: (Boolean) -> Unit) = onLoadSuccess(false)

    // todo show gdpr popup
    override fun showGdpr(gdprResult: (AdConsentStatus) -> Unit) = gdprResult(AdConsentStatus.NON_PERSONALIZED_ERROR)

    companion object {
        const val PREFERENCES_NAME = "GDPRConsent"
        const val PREFERENCES_KEY = "AdConsentStatus"
    }
}
