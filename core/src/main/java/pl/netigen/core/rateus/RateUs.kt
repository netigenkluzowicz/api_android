package pl.netigen.core.rateus

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import pl.netigen.core.utils.Utils
import pl.netigen.coreapi.rateus.IRateUs

/**
 * [IRateUs] implementation
 *
 * @property appCompatActivity [AppCompatActivity] context for this module
 */
class RateUs private constructor(
    private val appCompatActivity: AppCompatActivity,
    override val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG
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

    override fun doNotShowRateUsAgain() = sharedPreferences.edit().putBoolean(KEY_IS_RATE_US_OPEN, false).apply()

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
        RateFragment.newInstance({ clickYes() }, { clickNo() }, { clickLater() }).show(appCompatActivity.supportFragmentManager, "RateUsDialog")
    }

    override fun clickYes() {
        doNotShowRateUsAgain()
        Utils.openMarketLink(appCompatActivity, appCompatActivity.packageName)
    }

    override fun clickNo() {
        doNotShowRateUsAgain()
    }

    override fun clickLater() = sharedPreferences.edit().putInt(KEY_NUMBER_OF_OPENINGS, 0).apply()

    class Builder(private val appCompatActivity: AppCompatActivity) {
        fun createRateUs(): RateUs = RateUs(appCompatActivity)
    }
}