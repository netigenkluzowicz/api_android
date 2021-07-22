package pl.netigen.hms.gdpr

import android.app.Application
import android.content.Context
import com.huawei.hms.ads.consent.bean.AdProvider
import com.huawei.hms.ads.consent.constant.ConsentStatus
import com.huawei.hms.ads.consent.inter.Consent
import com.huawei.hms.ads.consent.inter.ConsentUpdateListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import pl.netigen.coreapi.gdpr.*
import timber.log.Timber

class GDPRConsentImpl(private val application: Application) : IGDPRConsent, IGDPRTexts by ConstGDPR {
    override val adConsentStatus: Flow<AdConsentStatus> = flow {
        val value =
                application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getInt(
                        PREFERENCES_KEY, AdConsentStatus.UNINITIALIZED.ordinal
                )
        emit(AdConsentStatus.values().getOrElse(value) { AdConsentStatus.UNINITIALIZED })
    }

    override fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus> =
            callbackFlow {
                val callback = object : ConsentUpdateListener {
                    override fun onSuccess(consentStatus: ConsentStatus?, isNeedConsent: Boolean, adProviders: MutableList<AdProvider>?) {
                        try {
                            Timber.d("consentStatus = [$consentStatus], isNeedConsent = [$isNeedConsent], adProviders = [$adProviders]")
                            sendBlocking(if (isNeedConsent) CheckGDPRLocationStatus.UE else CheckGDPRLocationStatus.NON_UE)
                        } catch (e: Exception) {
                        } finally {
                            channel.close()
                        }
                    }

                    override fun onFail(errorDescription: String?) {
                        try {
                            Timber.d("errorDescription = [$errorDescription]")
                            sendBlocking(CheckGDPRLocationStatus.ERROR)
                        } catch (e: Exception) {
                        } finally {
                            channel.close()
                        }
                    }
                }
                Consent.getInstance(application).requestConsentUpdate(callback)
                awaitClose {}
            }

    override fun saveAdConsentStatus(adConsentStatus: AdConsentStatus) {
        Timber.d("adConsentStatus = [$adConsentStatus]")
        val personalized = when (adConsentStatus) {
            AdConsentStatus.PERSONALIZED_NON_UE -> ConsentStatus.PERSONALIZED
            AdConsentStatus.PERSONALIZED_SHOWED -> ConsentStatus.PERSONALIZED
            AdConsentStatus.NON_PERSONALIZED_SHOWED -> ConsentStatus.NON_PERSONALIZED
            AdConsentStatus.UNINITIALIZED -> ConsentStatus.UNKNOWN
        }
        Consent.getInstance(application).setConsentStatus(personalized)
        application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt(PREFERENCES_KEY, adConsentStatus.ordinal)
                .apply()
    }

    override fun loadForm() = flowOf(false)

    override fun showForm() = flowOf(false)

    companion object {
        const val PREFERENCES_NAME = "GDPRConsent"
        const val PREFERENCES_KEY = "AdConsentStatus"
    }
}