package pl.netigen.core.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.view.View
import android.webkit.WebView
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
import pl.netigen.core.survey.Survey
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.GDPRClickListener
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainActivity
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.KEY_LAST_LAUNCH_TIME_COUNTER
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.KEY_NUMBER_OF_OPENINGS
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.SHARED_PREFERENCES_NAME
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.SPLASH_COUNTER_REFRESH_TIME_LIMIT_MS
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.SPLASH_COUNTER_REFRESH_TIME_LIMIT_MS_DEBUG
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.UPDATE_REQUEST_CODE
import pl.netigen.coreapi.main.ICoreMainVM
import pl.netigen.coreapi.rateus.IRateUs
import pl.netigen.coreapi.survey.ISurvey
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


    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    override val openingCounter
        get() = sharedPreferences.getInt(KEY_NUMBER_OF_OPENINGS, 0)

    override fun onSplashOpened() {
        Timber.d("()")
        _splashActive = true
        hideAds()
    }

    override val rateUs by lazy { RateUs.Builder(this).createRateUs() }

    override val survey by lazy { Survey.Builder(this).createSurvey() }

    /**
     * It's called when [CoreSplashFragment] is closed
     *
     */
    override fun onSplashClosed() {
        Timber.d("()")
        _splashActive = false
        if (noAdsActive) hideAds() else showAds()
        increaseOpeningCounter()
        checkRateUs()
        checkSurvey()
        checkForUpdate()
    }

    /**
     * Is be called in [onSplashOpened]
     *
     * @see [ISurvey.openAskForSurveyDialogIfNeeded]
     *
     * @return If Survey should be showed now
     */
    override fun checkSurvey() = survey.openAskForSurveyDialogIfNeeded(openingCounter)

    /**
     * It's called when you should open Fragment with [WebView] for displaying Survey
     * @see [Survey.showSurvey]
     */
    abstract override fun openSurveyFragment()

    /**
     * Is be called in [onSplashOpened]
     *
     * @see [IRateUs.openRateDialogIfNeeded]
     *
     * @return If Rate Us should be showed now
     */
    override fun checkRateUs(): Boolean = rateUs.openRateDialogIfNeeded()

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
        coreMainVM.bannerAd.onCreate(this)
    }

    open fun checkForUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(application)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            Timber.d("appUpdateInfo = [$appUpdateInfo]")
            val updateAvailability = appUpdateInfo.updateAvailability()
            when (updateAvailability) {
                UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                    Timber.d("DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS")
                    if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                        popupSnackbarForCompleteUpdate(appUpdateManager)
                    }
                }

                UpdateAvailability.UNKNOWN -> {
                    Timber.d("UNKNOWN")
                }

                UpdateAvailability.UPDATE_AVAILABLE -> {
                    Timber.d("UPDATE_AVAILABLE")
                }

                UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                    Timber.d("UPDATE_NOT_AVAILABLE")
                }
            }
            if (updateAvailability == UpdateAvailability.UPDATE_AVAILABLE &&
                (appUpdateInfo.clientVersionStalenessDays() ?: -1) >= coreMainVM.daysForFlexibleUpdate &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                requestUpdate(appUpdateManager, appUpdateInfo)
            }
        }
        val listener = object : InstallStateUpdatedListener {
            override fun onStateUpdate(state: InstallState) {
                when (state.installStatus()) {
                    InstallStatus.DOWNLOADED -> {
                        Timber.d("DOWNLOADED")
                        popupSnackbarForCompleteUpdate(appUpdateManager)
                        appUpdateManager.unregisterListener(this)
                    }

                    InstallStatus.DOWNLOADING -> {
                        val bytesDownloaded = state.bytesDownloaded()
                        val totalBytesToDownload = state.totalBytesToDownload()
                        Timber.d("DOWNLOADING: $bytesDownloaded / $totalBytesToDownload")
                    }

                    InstallStatus.CANCELED -> {
                        Timber.d("CANCELED")
                        appUpdateManager.unregisterListener(this)
                    }

                    InstallStatus.FAILED -> {
                        Timber.d("FAILED")
                        appUpdateManager.unregisterListener(this)
                    }

                    InstallStatus.INSTALLED -> {
                        Timber.d("INSTALLED")
                        appUpdateManager.unregisterListener(this)
                    }

                    InstallStatus.INSTALLING -> {
                        Timber.d("INSTALLING")
                    }

                    InstallStatus.PENDING -> {
                        Timber.d("PENDING")
                    }

                    InstallStatus.UNKNOWN -> {
                        Timber.d("UNKNOWN")
                        appUpdateManager.unregisterListener(this)
                    }
                }
            }
        }
        appUpdateManager.registerListener(listener)
    }

    private fun requestUpdate(appUpdateManager: AppUpdateManager, appUpdateInfo: AppUpdateInfo) {
        Timber.d("appUpdateManager = [$appUpdateManager], appUpdateInfo = [$appUpdateInfo]")
        appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            AppUpdateType.FLEXIBLE,
            this,
            UPDATE_REQUEST_CODE,
        )
    }

    private fun popupSnackbarForCompleteUpdate(appUpdateManager: AppUpdateManager) {
        Timber.d("appUpdateManager = [$appUpdateManager]")
        val contentView: View? = findViewById(android.R.id.content)
        contentView?.let {
            val snackbar = Snackbar.make(
                it,
                "An update has just been downloaded.",
                Snackbar.LENGTH_INDEFINITE,
            ).apply {
                setAction("RESTART") { appUpdateManager.completeUpdate() }
            }
            snackbar.anchorView = findViewById(resources.getIdentifier(coreMainVM.bannerLayoutIdName, "id", packageName))
            snackbar.show()
        }
    }

    override fun showGdprPopUp() {
        Timber.d("()")
        val fragment = GDPRDialogFragment.newInstance()
        fragment.show(supportFragmentManager.beginTransaction().addToBackStack(null), null)
        fragment.setIsPayOptions(coreMainVM.isNoAdsAvailable)
        fragment.bindGDPRListener(
            object : GDPRClickListener {
                override fun onConsentAccepted(personalizedAds: Boolean) {
                    coreMainVM.personalizedAdsEnabled = personalizedAds
                    val adConsentStatus = if (personalizedAds) AdConsentStatus.PERSONALIZED_SHOWED else AdConsentStatus.NON_PERSONALIZED_SHOWED
                    coreMainVM.saveAdConsentStatus(adConsentStatus)
                }

                override fun clickPay() {
                    fragment.dismissAllowingStateLoss()
                    coreMainVM.makeNoAdsPayment(this@CoreMainActivity)
                }
            },
        )
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

    override fun increaseOpeningCounter() {
        val lastTimeOnSplashTimeMs = sharedPreferences.getLong(KEY_LAST_LAUNCH_TIME_COUNTER, 0L)
        Timber.d("()$lastTimeOnSplashTimeMs")
        val limit = if (0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
            SPLASH_COUNTER_REFRESH_TIME_LIMIT_MS_DEBUG
        } else {
            SPLASH_COUNTER_REFRESH_TIME_LIMIT_MS
        }
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastTimeOnSplashTimeMs > limit) {
            sharedPreferences.edit().putInt(KEY_NUMBER_OF_OPENINGS, openingCounter + 1).apply()
            sharedPreferences.edit().putLong(KEY_LAST_LAUNCH_TIME_COUNTER, currentTimeMillis).apply()
            Timber.d("launched")
        }
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
