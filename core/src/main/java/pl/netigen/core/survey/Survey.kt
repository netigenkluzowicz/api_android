package pl.netigen.core.survey

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.rateus.IRateUs
import pl.netigen.coreapi.survey.ISurvey
import pl.netigen.coreapi.survey.SurveyData
import timber.log.Timber

/**
 * [IRateUs] implementation
 *
 * @property appCompatActivity [AppCompatActivity] context for this module
 */
class Survey private constructor(
    private val appCompatActivity: AppCompatActivity,
    private val numberOfChecksBeforeShowingDialog: Int
) : ISurvey {
    companion object {
        private const val SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs"
        private const val KEY_SURVEY_OPEN = "KEY_SURVEY_OPEN"
        private const val NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG = 6
    }

    private val sharedPreferences: SharedPreferences by lazy {
        appCompatActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    override fun openAskForSurveyDialogIfNeeded(launchCount: Int): Boolean {
        if (shouldOpenAskFragment(launchCount)) {
            openAskForSurveyDialog()
            return true
        }
        return false
    }

    fun shouldOpenAskFragment(launchCount: Int): Boolean {
        if (appCompatActivity.supportFragmentManager.isStateSaved) return false
        return launchCount >= numberOfChecksBeforeShowingDialog && sharedPreferences.getBoolean(KEY_SURVEY_OPEN, true)
    }

    private fun openAskForSurveyDialog() {
        Timber.d("()")
        AskForSurveyFragment.newInstance({ clickYes() }, { clickNo() }).show(appCompatActivity.supportFragmentManager, "AskForSurveyDialog")

    }

    override fun clickYes() {
        doNotShowRateUsAgain()
        openSurveyDialog()
    }

    override fun clickNo() {
        doNotShowRateUsAgain()
    }

    private fun doNotShowRateUsAgain() {
        sharedPreferences.edit().putBoolean(KEY_SURVEY_OPEN, false).apply()
    }

    override fun openSurveyDialog() {
        SurveyFragment.newInstance({ clickSend() }, { clickCancel() }).show(appCompatActivity.supportFragmentManager, "SurveyDialog")
    }

    override fun clickSend(surveyData: SurveyData)  {
        TODO("Not yet implemented")
    }

    override fun clickCancel() {
        TODO("Not yet implemented")
    }

    class Builder(
        private val coreMainActivity: CoreMainActivity,
        private val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG
    ) {
        fun createSurvey(): Survey = Survey(coreMainActivity, numberOfChecksBeforeShowingDialog)
    }
}