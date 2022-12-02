package pl.netigen.core.survey

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.newlanguage.ChangeLanguageHelper
import pl.netigen.coreapi.rateus.IRateUs
import pl.netigen.coreapi.survey.ISurvey
import pl.netigen.coreapi.survey.ISurvey.Companion.FORCE_SHOW
import pl.netigen.coreapi.survey.ISurvey.Companion.NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG
import pl.netigen.coreapi.survey.SurveyAction
import timber.log.Timber

/**
 * [IRateUs] implementation
 *
 * @property appCompatActivity [AppCompatActivity] context for this module
 */
class Survey private constructor(
    private val appCompatActivity: AppCompatActivity,
    private val numberOfChecksBeforeShowingDialog: Int,
) : ISurvey {
    companion object {
        private const val SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs"
        private const val KEY_SURVEY_OPEN = "KEY_SURVEY_OPEN"
        private const val interfaceName = "Android"
    }

    private val sharedPreferences: SharedPreferences by lazy { appCompatActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE) }

    @SuppressLint("SetJavaScriptEnabled")
    override fun showSurvey(webView: WebView, appVersionName: String, onNextAction: (surveyAction: SurveyAction, exitSurvey: Boolean) -> Unit) {
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(SurveyInterface(onNextAction), interfaceName)
        val context = webView.context
        val packageName = context.packageName
        val locale = ChangeLanguageHelper.getCurrentAppLocale(context)
        val apiLink = "https://apis.netigen.eu/survey-webview"
        val url = "$apiLink?packageName=$packageName&appVersion=$appVersionName&platform=android&locale=$locale"
        webView.loadUrl(url)
    }

    override fun openAskForSurveyDialogIfNeeded(launchCount: Int): Boolean {
        if (shouldOpenAskFragment(launchCount)) {
            openAskForSurveyDialog()
            return true
        }
        return false
    }

    fun shouldOpenAskFragment(launchCount: Int): Boolean {
        if (launchCount == FORCE_SHOW) return true
        if (appCompatActivity.supportFragmentManager.isStateSaved) return false
        return launchCount >= numberOfChecksBeforeShowingDialog && sharedPreferences.getBoolean(KEY_SURVEY_OPEN, true)
    }

    private fun openAskForSurveyDialog() {
        Timber.d("()")
        AskForSurveyFragment.newInstance({ clickYes() }, { clickNo() }).show(appCompatActivity.supportFragmentManager, "AskForSurveyDialog")
    }

    override fun clickYes() {
        doNotShowSurveyAgain()
    }

    override fun clickNo() {
        doNotShowSurveyAgain()
    }

    private fun doNotShowSurveyAgain() {
        sharedPreferences.edit().putBoolean(KEY_SURVEY_OPEN, false).apply()
    }

    class Builder(
        private val coreMainActivity: CoreMainActivity,
        private val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG,
    ) {
        fun createSurvey(): Survey = Survey(coreMainActivity, numberOfChecksBeforeShowingDialog)
    }
}
