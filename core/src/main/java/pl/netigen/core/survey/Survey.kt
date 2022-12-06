package pl.netigen.core.survey

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
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
 * @property coreMainActivity [AppCompatActivity] context for this module
 */
class Survey private constructor(
    private val coreMainActivity: CoreMainActivity,
    private val openingInterval: Int,
) : ISurvey {

    private val sharedPreferences: SharedPreferences by lazy { coreMainActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE) }

    override fun openAskForSurveyDialogIfNeeded(launchCount: Int): Boolean {
        if (shouldOpenAskFragment(launchCount)) {
            openAskForSurveyDialog()
            return true
        }
        return false
    }

    override fun shouldOpenAskFragment(launchCount: Int): Boolean {
        if (coreMainActivity.coreMainVM.isConnectedOrConnecting.not()) return false
        if (launchCount == FORCE_SHOW) return true
        if (coreMainActivity.supportFragmentManager.isStateSaved) return false
        return launchCount >= openingInterval && launchCount % openingInterval == 0 && sharedPreferences.getBoolean(KEY_SURVEY_OPEN, true)
    }

    private fun openAskForSurveyDialog() {
        Timber.d("()")
        AskForSurveyFragment.newInstance({ clickYes() }, { clickNo() }).show(coreMainActivity.supportFragmentManager, "AskForSurveyDialog")
    }

    override fun clickYes() {
        doNotShowSurveyAgain()
        coreMainActivity.openSurveyFragment()
    }

    override fun clickNo() = doNotShowSurveyAgain()

    override fun clickLater() = Unit

    private fun doNotShowSurveyAgain() = sharedPreferences.edit().putBoolean(KEY_SURVEY_OPEN, false).apply()

    class Builder(
        private val coreMainActivity: CoreMainActivity,
        private val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG,
    ) {
        fun createSurvey(): Survey = Survey(coreMainActivity, numberOfChecksBeforeShowingDialog)
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs"
        private const val KEY_SURVEY_OPEN = "KEY_SURVEY_OPEN"
        private const val interfaceName = "Android"

        @SuppressLint("SetJavaScriptEnabled")
        fun showSurvey(webView: WebView, appVersionName: String, onNextAction: (surveyAction: SurveyAction, exitSurvey: Boolean) -> Unit) {
            webView.settings.javaScriptEnabled = true
            webView.addJavascriptInterface(SurveyInterface(onNextAction), interfaceName)
            val context = webView.context
            val packageName = context.packageName
            val locale = ChangeLanguageHelper.getCurrentAppLocale(context)
            val apiLink = "https://apis.netigen.eu/survey-webview"
            val url = "$apiLink?packageName=$packageName&appVersion=$appVersionName&platform=android&locale=$locale"
            webView.loadUrl(url)
            webView.webViewClient = object : WebViewClient() {
                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        Timber.d("xxx.+view = [$view], request = [$request], error = [${error?.description}]")
                    }
                }

                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                    Timber.d("xxx.+view = [$view], errorCode = [$errorCode], description = [$description], failingUrl = [$failingUrl]")
                }
            }
        }
    }
}
