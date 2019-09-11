package pl.netigen.netigenapi

import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity

import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import com.google.android.gms.ads.MobileAds

import pl.netigen.rodo.ConstRodo
import pl.netigen.rodo.GDPRDialogFragment

abstract class BaseSplashActivity : AppCompatActivity(), ISplashActivity, AdmobIds, GDPRDialogFragment.GDPRClickListener {

    var admobManager: AdmobManager? = null
    var consentFinished: Boolean = false

    private var gdprDialogFragment: GDPRDialogFragment? = null
    private var canCommitFragment: Boolean = false
    private lateinit var initAdmobHandler: Handler

    abstract fun isNoAdsPaymentAvailable(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        Config.initialize(getConfigBuilder())
        if (!isNoAdsPaymentAvailable()) {
            Config.setNoAdsBought(false)
            if (shouldShowInterstitialAd())
                showConsent()
            else {
                clickNo()
                startNextActivity()
            }
        }
    }

    override fun showFullscreen(): Boolean {
        return super.showFullscreen()
    }

    private fun showConsent() {
        val consentInformation = ConsentInformation.getInstance(this)
        consentInformation.requestConsentInfoUpdate(getPublisherIds(), object : ConsentInfoUpdateListener {
            override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                val isInEea = ConsentInformation.getInstance(this@BaseSplashActivity).isRequestLocationInEeaOrUnknown
                ConstRodo.setIsInEea(isInEea)
                if (isInEea && consentInformation.consentStatus == ConsentStatus.UNKNOWN) {
                    initRodoFragment()
                } else {
                    consentFinished = true
                    startAdmobSplash()
                }
            }

            override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                startAdmobSplash()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        canCommitFragment = false
        admobManager?.splashScreenOnStop()
    }

    override fun onStart() {
        super.onStart()
        canCommitFragment = true
        admobManager?.splashScreenOnStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        admobManager?.splashScreenOnDestroy()
    }

    private fun initRodoFragment() {
        if (canCommitFragment) {
            gdprDialogFragment = GDPRDialogFragment.newInstance()
            gdprDialogFragment!!.setIsPayOptions(isNoAdsPaymentAvailable())
            gdprDialogFragment!!.show(supportFragmentManager.beginTransaction().addToBackStack(null), "")
        }
    }

    private fun startAdmobSplash() {
        if (shouldShowInterstitialAd()) {
            if (!::initAdmobHandler.isInitialized) {
                initAdmobHandler = Handler()
                initAdmobHandler.post { this.initAdmob() }
            }
        } else {
            clickNo()
            startNextActivity()
        }
    }

    private fun startNextActivity() {
        startActivity(getIntentToLaunch())
        finish()
    }

    internal open fun initAdmob() {
        MobileAds.initialize(this, admobAppID)
        admobManager = AdmobManager.create(bannerId, fullScreenId, this)
        admobManager?.splashScreenOnCreate(getIntentToLaunch())
    }

    override fun onBackPressed() {
        gdprDialogFragment?.let { it.showAdmobText() }
    }

    override fun clickYes() {
        ConsentInformation.getInstance(this).consentStatus = ConsentStatus.PERSONALIZED
        closeRodoFragment()
        startAdmobSplash()
        consentFinished = true
    }

    private fun closeRodoFragment() {
        gdprDialogFragment?.let { super.onBackPressed() }
        gdprDialogFragment = null
    }

    override fun clickNo() {
        ConsentInformation.getInstance(this).consentStatus = ConsentStatus.NON_PERSONALIZED
    }

    override fun clickAcceptPolicy() {
        closeRodoFragment()
        startAdmobSplash()
        consentFinished = true
    }

    override fun onNoAdsPaymentProcessingFinished(noAdsBought: Boolean) {
        closeRodoFragment()
        Config.setNoAdsBought(noAdsBought)
        if (noAdsBought)
            startNextActivity()
        else
            showConsent()
    }
}