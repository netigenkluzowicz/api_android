package pl.netigen.core.survey

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import pl.netigen.core.R
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.newlanguage.ChangeLanguageHelper
import pl.netigen.coreapi.main.ICoreMainActivity
import pl.netigen.coreapi.rateus.IRateUs
import pl.netigen.coreapi.survey.ISurvey
import pl.netigen.coreapi.survey.ISurvey.Companion.NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG
import pl.netigen.coreapi.survey.SurveyEvent
import pl.netigen.coreapi.survey.SurveyInterface
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
            resetOpenTimer()
            openAskForSurveyDialog()
            return true
        }
        return false
    }

    private fun resetOpenTimer() {
        sharedPreferences.edit().putLong(ICoreMainActivity.KEY_LAST_LAUNCH_TIME_COUNTER, 0L).apply()
    }

    override fun shouldOpenAskFragment(launchCount: Int): Boolean = false

    private fun openAskForSurveyDialog() {
        Timber.d("()")
        AskForSurveyFragment.newInstance { clickYes() }.show(coreMainActivity.supportFragmentManager, "AskForSurveyDialog")
    }

    override fun clickYes() {
        doNotShowSurveyAgain()
        coreMainActivity.openSurveyFragment()
    }

    override fun clickNo() = doNotShowSurveyAgain()

    override fun clickLater() = Unit

    private fun doNotShowSurveyAgain() = sharedPreferences.edit().putBoolean(KEY_SURVEY_OPEN, false).apply()

    /**
     * Launches Survey in WebView implemented in JS
     *
     * @param webView for showing survey content
     * @param appVersionName current app release version name, use [BuildConfig.VERSION_NAME] for it
     * @param onNextAction callback with [SurveyEvent]s from survey
     *
     * @see <a href="https://github.com/netigenkluzowicz/apis_strapi/blob/develop/documentation/webview-survey.md">Webview survey</a>
     */
    override fun showSurvey(webView: WebView, appVersionName: String, onNextAction: (surveyEvent: SurveyEvent) -> Unit) =
        Survey.showSurvey(webView, appVersionName, onNextAction)

    class Builder(
        private val coreMainActivity: CoreMainActivity,
        private val numberOfChecksBeforeShowingDialog: Int = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG,
    ) {
        fun createSurvey(): Survey = Survey(coreMainActivity, numberOfChecksBeforeShowingDialog)
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs"
        private const val KEY_SURVEY_OPEN = "KEY_SURVEY_OPEN"
        private const val SURVEY_API_LINK = "https://apis.netigen.eu/survey-webview"
        private const val interfaceName = "Android"
        private val noInternetLayoutId = ViewCompat.generateViewId()

        /**
         * Launches Survey in WebView implemented in JS
         *
         * @param webView for showing survey content
         * @param appVersionName current app release version name, use [BuildConfig.VERSION_NAME] for it
         * @param onNextAction callback with [SurveyEvent]s from survey
         *
         * @see <a href="https://github.com/netigenkluzowicz/apis_strapi/blob/develop/documentation/webview-survey.md">Webview survey</a>
         */
        @SuppressLint("SetJavaScriptEnabled")
        fun showSurvey(webView: WebView, appVersionName: String, onNextAction: (surveyAction: SurveyEvent) -> Unit) {
            val context = webView.context
            webView.settings.javaScriptEnabled = true
            val action: (surveyAction: SurveyEvent) -> Unit = {
                onNextAction(it)
                if (it is SurveyEvent.Finish) saveAsFinished(context)
            }
            webView.addJavascriptInterface(SurveyInterface(action), interfaceName)
            val packageName = context.packageName
            val locale = ChangeLanguageHelper.getCurrentAppLocale(context)
            val apiLink = SURVEY_API_LINK
            val url = "$apiLink?packageName=$packageName&appVersion=$appVersionName&platform=android&locale=$locale"
            webView.loadUrl(url)
            val parentView = webView.parent as ViewGroup
            webView.webViewClient = object : WebViewClient() {
                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) showErrorInfo(webView, context, onNextAction, url, parentView)
                }

                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) =
                    showErrorInfo(webView, context, onNextAction, url, parentView)
            }
        }

        private fun saveAsFinished(context: Context) {
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit().putBoolean(KEY_SURVEY_OPEN, false).apply()
        }

        private fun showErrorInfo(
            webView: WebView,
            context: Context,
            onNextAction: (surveyAction: SurveyEvent) -> Unit,
            url: String,
            parentView: ViewGroup,
        ) {
            val view = parentView.findViewById<View?>(noInternetLayoutId)
            if (view != null) return
            val inflater = LayoutInflater.from(context)
            parentView.removeView(webView)
            val info = inflater.inflate(R.layout.no_internet_connection_survey_layout, parentView, false)
            info.id = noInternetLayoutId
            parentView.addView(info)
            info.findViewById<ImageView>(R.id.closeSurvey).setOnClickListener { onNextAction(SurveyEvent.QuitFromError()) }
            info.findViewById<TextView>(R.id.confirmButton).setOnClickListener {
                parentView.removeAllViews()
                if (webView.parent == null) parentView.addView(webView)
                webView.clearHistory()
                webView.clearCache(true)
                webView.loadUrl(url)
            }
        }
    }
}
