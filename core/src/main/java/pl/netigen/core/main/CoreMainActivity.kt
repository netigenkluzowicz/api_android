package pl.netigen.core.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import pl.netigen.core.gdpr.GDPRDialogFragment
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainActivity
import pl.netigen.coreapi.main.ICoreMainVM
import pl.netigen.extensions.observe
import timber.log.Timber

abstract class CoreMainActivity : AppCompatActivity(), ICoreMainActivity {
    override val canCommitFragments: Boolean
        get() = !supportFragmentManager.isStateSaved

    private var _noAdsActive: Boolean = false
    override val noAdsActive: Boolean
        get() = _noAdsActive

    private var _splashActive: Boolean = false
    override val splashActive: Boolean
        get() = _splashActive

    override val coreMainVM: ICoreMainVM by viewModels<CoreMainVM> { viewModelFactory }

    override fun onSplashOpened() {
        Timber.d("()")
        _splashActive = true
        hideAds()
    }

    override fun onSplashClosed() {
        Timber.d("()")
        _splashActive = false
        if (noAdsActive) hideAds() else showAds()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("savedInstanceState = [$savedInstanceState]")
        coreMainVM.noAdsActive.asLiveData().observe(this, this::onNoAdsChanged)
        coreMainVM.showGdprResetAds.observe(this) {
            if (canCommitFragments) {
                showGdprPopUp()
            }
        }
    }

    override fun showGdprPopUp() {
        Timber.d("()")
        val fragment = GDPRDialogFragment.newInstance()
        fragment.show(supportFragmentManager.beginTransaction().addToBackStack(null), null)
        fragment.setIsPayOptions(coreMainVM.isNoAdsAvailable)
        fragment.bindGDPRListener(object : GDPRDialogFragment.GDPRClickListener {
            override fun onConsentAccepted(personalizedAds: Boolean) {
                coreMainVM.personalizedAdsEnabled = personalizedAds
                val adConsentStatus = if (personalizedAds) AdConsentStatus.PERSONALIZED_SHOWED else AdConsentStatus.NON_PERSONALIZED_SHOWED
                coreMainVM.saveAdConsentStatus(adConsentStatus)
            }

            override fun clickPay() {
                fragment.dismissAllowingStateLoss()
                coreMainVM.makeNoAdsPayment(this@CoreMainActivity)
            }
        })
    }

    @CallSuper
    override fun onResumeFragments() {
        super.onResumeFragments()
        Timber.d("()")
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        coreMainVM.start()
    }

    override fun onNoAdsChanged(noAdsActive: Boolean) {
        this._noAdsActive = noAdsActive
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

}