package pl.netigen.core.rateus

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.review.ReviewManagerFactory
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.utils.Utils
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.KEY_IS_RATE_US_OPEN
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.KEY_NUMBER_OF_OPENINGS
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.NUMBER_OF_CHECKS_BEFORE_SHOWING_RATE_US
import pl.netigen.coreapi.main.ICoreMainActivity.Companion.SHARED_PREFERENCES_NAME
import pl.netigen.coreapi.rateus.IRateUs
import timber.log.Timber

/**
 * [IRateUs] implementation
 *
 * @property coreMainActivity [AppCompatActivity] context for this module
 */
class RateUs private constructor(
    private val coreMainActivity: CoreMainActivity,
    override val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_RATE_US,
) : IRateUs {


    private val sharedPreferences: SharedPreferences by lazy {
        coreMainActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }


    override fun shouldOpenRateUs(): Boolean {
        if (coreMainActivity.supportFragmentManager.isStateSaved) return false
        return sharedPreferences.getBoolean(KEY_IS_RATE_US_OPEN, true)
    }

    override fun doNotShowRateUsAgain() = sharedPreferences.edit().putBoolean(KEY_IS_RATE_US_OPEN, false).apply()

    override fun openRateDialogIfNeeded(): Boolean {
        if (shouldOpenRateUs()) {
            val counter = coreMainActivity.openingCounter
            if (counter % numberOfChecksBeforeShowingDialog == numberOfChecksBeforeShowingDialog - 1) {
                openRateDialog()
                return true
            }
        }
        return false
    }

    override fun openRateDialog() {
        val manager = ReviewManagerFactory.create(coreMainActivity)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            Timber.d("task = [$task]")
            if (task.isSuccessful) {
                Timber.d("Show New Rate Us")
                val reviewInfo = task.result
                manager.launchReviewFlow(coreMainActivity, reviewInfo)
            }
        }
    }

    override fun clickYes() {
        doNotShowRateUsAgain()
        Utils.openMarketLink(coreMainActivity, coreMainActivity.packageName)
    }

    override fun clickNo() {
        doNotShowRateUsAgain()
    }

    override fun clickLater() = sharedPreferences.edit().putInt(KEY_NUMBER_OF_OPENINGS, 0).apply()

    class Builder(
        private val coreMainActivity: CoreMainActivity,
        private val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_RATE_US,
    ) {
        fun createRateUs(): RateUs = RateUs(coreMainActivity, numberOfChecksBeforeShowingDialog)
    }
}
