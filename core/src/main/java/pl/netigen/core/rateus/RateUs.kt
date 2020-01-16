package pl.netigen.core.rateus

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import pl.netigen.core.utils.Utils

class RateUs private constructor(private val appCompatActivity: AppCompatActivity) {
    companion object {
        private const val SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs"
        private const val KEY_NUMBER_OF_OPENINGS = "KEY_NUMBER_OF_OPENINGS"
        private const val KEY_IS_RATE_US_OPEN = "KEY_IS_RATE_US_OPEN"
        private const val NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG = 4
    }

    private val sharedPreferences: SharedPreferences by lazy {
        appCompatActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private val openingCounter = sharedPreferences.getInt(KEY_NUMBER_OF_OPENINGS, 0)

    private fun increaseOpeningCounter() = sharedPreferences.edit().putInt(KEY_NUMBER_OF_OPENINGS, openingCounter + 1).apply()
    private fun shouldOpenRateUs() = sharedPreferences.getBoolean(KEY_IS_RATE_US_OPEN, true)
    private fun doNotShowRateUsAgain() = sharedPreferences.edit().putBoolean(KEY_IS_RATE_US_OPEN, false).apply()

    fun openRateDialogIfNeeded(): Boolean {
        if (shouldOpenRateUs()) {
            val counter = openingCounter
            increaseOpeningCounter()
            if (counter % NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG == NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG - 1) {
                openRateDialog()
                return true
            }
        }
        return false
    }

    fun openRateDialog() {
        RateFragment.newInstance({ clickYes() }, { clickNo() }, { clickLater() }).show(appCompatActivity.supportFragmentManager, "RateUsDialog")
    }

    private fun clickYes() {
        doNotShowRateUsAgain()
        Utils.openMarketLink(appCompatActivity, appCompatActivity.packageName)
    }

    private fun clickNo() {
        doNotShowRateUsAgain()
    }

    private fun clickLater() {}

    class Builder(private val appCompatActivity: AppCompatActivity) {
        fun createRateUs(): RateUs = RateUs(appCompatActivity)
    }
}