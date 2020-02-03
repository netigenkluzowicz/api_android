package pl.netigen.core.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import pl.netigen.core.gdpr.GDPRDialogFragment
import pl.netigen.core.splash.CoreSplashFragment
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainVM
import pl.netigen.extensions.observe

abstract class CoreMainActivity : AppCompatActivity() {
    private var noAdsActive: Boolean = false
    private var splashActive: Boolean = false
    abstract val viewModelFactory: ViewModelProvider.Factory
    val coreMainVM: ICoreMainVM by viewModels<CoreMainVM> { viewModelFactory }
    abstract val coreSplashFragment: CoreSplashFragment

    open fun onSplashOpened() {
        splashActive = true
        hideAds()
    }

    open fun onSplashClosed() {
        splashActive = false
        if (noAdsActive) hideAds() else showAds()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coreMainVM.noAdsActive.asLiveData().observe(this, this::onNoAdsChanged)
        coreMainVM.showGdprResetAds.observe(this) { showGdprPopUp() }
    }

    private fun showGdprPopUp() {
        val fragment = GDPRDialogFragment.newInstance()
        fragment.show(supportFragmentManager.beginTransaction().addToBackStack(null), null)
        fragment.setIsPayOptions(!noAdsActive)
        fragment.bindGDPRListener(object : GDPRDialogFragment.GDPRClickListener {
            override fun onConsentAccepted(personalizedAds: Boolean) {
                coreMainVM.personalizedAdsEnabled = personalizedAds
                val adConsentStatus = if (personalizedAds) AdConsentStatus.PERSONALIZED_SHOWED else AdConsentStatus.NON_PERSONALIZED_SHOWED
                coreMainVM.saveAdConsentStatus(adConsentStatus)
            }

            override fun clickPay() = coreMainVM.makeNoAdsPayment(this@CoreMainActivity)
        })
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        coreMainVM.start()
    }

    open fun onNoAdsChanged(noAdsActive: Boolean) {
        this.noAdsActive = noAdsActive
        if (splashActive) return
        if (noAdsActive) {
            hideAds()
        } else {
            showAds()
            coreMainVM.interstitialAd.loadIfShouldBeLoaded()
        }
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            coreMainVM.onActivityResult(requestCode, resultCode, data)
        }
    }

    abstract fun hideAds()
    abstract fun showAds()
}