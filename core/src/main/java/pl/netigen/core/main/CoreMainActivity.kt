package pl.netigen.core.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import pl.netigen.core.gdpr.GDPRDialogFragment
import pl.netigen.core.rateus.RateUs
import pl.netigen.core.splash.CoreSplashFragment
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.GDPRClickListener
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainActivity
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.UPDATE_REQUEST_CODE
import pl.netigen.coreapi.main.ICoreMainVM
import pl.netigen.extensions.observe
import timber.log.Timber

/**
 * Implements [ICoreMainActivity]
 */
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

    val rateUs by lazy { RateUs.Builder(this).createRateUs() }

    /**
     * It's called when [CoreSplashFragment] is closed
     *
     */
    override fun onSplashClosed() {
        Timber.d("()")
        _splashActive = false
        if (noAdsActive) hideAds() else showAds()
        rateUs.openRateDialogIfNeeded()
    }

    /**
     * Starts observing [ICoreMainVM.noAdsActive] and [ICoreMainVM.showGdprResetAds]
     *
     */
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
        checkForUpdate();
    }

    private fun checkForUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(application)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            Timber.d("appUpdateInfo = [$appUpdateInfo]")
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && (appUpdateInfo.clientVersionStalenessDays() ?: -1) >= coreMainVM.daysForFlexibleUpdate
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                requestUpdate(appUpdateManager, appUpdateInfo)
            }
        }
    }

    private fun requestUpdate(appUpdateManager: AppUpdateManager, appUpdateInfo: AppUpdateInfo) {
        appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo, AppUpdateType.FLEXIBLE, this, UPDATE_REQUEST_CODE
        )
        val listener = object : InstallStateUpdatedListener {
            val manager = appUpdateManager
            override fun onStateUpdate(state: InstallState) {
                Timber.d("state = [$state]")
                when (state.installStatus()) {
                    InstallStatus.DOWNLOADED -> {
                        popupSnackbarForCompleteUpdate(appUpdateManager)
                        manager.unregisterListener(this)
                    }
                    else -> manager.unregisterListener(this)
                }
            }
        }
        appUpdateManager.registerListener(listener)

    }

    private fun popupSnackbarForCompleteUpdate(appUpdateManager: AppUpdateManager) {
        val findViewById : View? = findViewById(android.R.id.content)
        findViewById?.let {
            Snackbar.make(
                it,
                "An update has just been downloaded.",
                Snackbar.LENGTH_INDEFINITE
            ).apply {
                setAction("RESTART") { appUpdateManager.completeUpdate() }
                show()
            }
        }
    }


    override fun showGdprPopUp() {
        Timber.d("()")
        val fragment = GDPRDialogFragment.newInstance()
        fragment.show(supportFragmentManager.beginTransaction().addToBackStack(null), null)
        fragment.setIsPayOptions(coreMainVM.isNoAdsAvailable)
        fragment.bindGDPRListener(object : GDPRClickListener {
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
    override fun onResume() {
        super.onResume()
        Timber.d("()")
        coreMainVM.interstitialAdIsInBackground(false)
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        Timber.d("()")
        coreMainVM.interstitialAdIsInBackground(true)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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