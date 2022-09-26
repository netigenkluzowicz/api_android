package pl.netigen.core.rateus

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.review.ReviewManagerFactory
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.utils.Utils
import pl.netigen.coreapi.main.Store
import pl.netigen.coreapi.rateus.IRateUs
import timber.log.Timber

/**
 * [IRateUs] implementation
 *
 * @property appCompatActivity [AppCompatActivity] context for this module
 */
class RateUs private constructor(
    private val appCompatActivity: AppCompatActivity,
    private val showOldDialog: Boolean = false,
    override val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG,
) : IRateUs {
    companion object {
        private const val SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs"
        private const val KEY_NUMBER_OF_OPENINGS = "KEY_NUMBER_OF_OPENINGS"
        private const val KEY_IS_RATE_US_OPEN = "KEY_IS_RATE_US_OPEN"
        private const val NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG = 4
    }

    private val sharedPreferences: SharedPreferences by lazy {
        appCompatActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    override val openingCounter
        get() = sharedPreferences.getInt(KEY_NUMBER_OF_OPENINGS, 0)

    override fun increaseOpeningCounter() = sharedPreferences.edit().putInt(KEY_NUMBER_OF_OPENINGS, openingCounter + 1).apply()
    override fun shouldOpenRateUs(): Boolean {
        if (appCompatActivity.supportFragmentManager.isStateSaved) return false
        return sharedPreferences.getBoolean(KEY_IS_RATE_US_OPEN, true)
    }

    override fun doNotShowRateUsAgain() {
        Timber.d("()")
        sharedPreferences.edit().putBoolean(KEY_IS_RATE_US_OPEN, false).apply()
    }

    override fun openRateDialogIfNeeded(): Boolean {
        if (shouldOpenRateUs()) {
            val counter = openingCounter
            increaseOpeningCounter()
            if (counter % numberOfChecksBeforeShowingDialog == numberOfChecksBeforeShowingDialog - 1) {
                openRateDialog()
                return true
            }
        }
        return false
    }

    override fun openRateDialog() {
        Timber.d("()")
        if (showOldDialog) {
            RateFragment.newInstance({ clickYes() }, { clickNo() }, { clickLater() }).show(appCompatActivity.supportFragmentManager, "RateUsDialog")
        } else {
            loadNewDialog()
        }
    }

    private fun loadNewDialog() {
        val manager = ReviewManagerFactory.create(appCompatActivity)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            Timber.d("task = [$task]")
            if (task.isSuccessful) {
                Timber.d("Show New Rate Us")
                val reviewInfo = task.result
                manager.launchReviewFlow(appCompatActivity, reviewInfo)
            }
        }
    }

    override fun clickYes() {
        doNotShowRateUsAgain()
        Utils.openMarketLink(appCompatActivity, appCompatActivity.packageName)
    }

    override fun clickNo() {
        doNotShowRateUsAgain()
    }

    override fun clickLater() = sharedPreferences.edit().putInt(KEY_NUMBER_OF_OPENINGS, 0).apply()

    class Builder(
        private val coreMainActivity: CoreMainActivity,
        private val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG,
    ) {
        fun createRateUs(): RateUs = RateUs(coreMainActivity, coreMainActivity.coreMainVM.store == Store.HUAWEI, numberOfChecksBeforeShowingDialog)
    }
}
