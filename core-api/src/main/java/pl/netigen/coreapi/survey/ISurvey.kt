package pl.netigen.coreapi.survey

import android.webkit.WebView

interface ISurvey {
    /**
     * Called on click in dialog asking for Survey
     *
     * Shows Survey dialog
     *
     */
    fun clickYes()

    /**
     * Called on click in for Survey dialog, after that asking will not be showed again
     *
     */
    fun clickNo()

    /**
     * Called on click in for Survey dialog, after that it will be showed again
     *
     */
    fun clickLater()

    /**
     * Checks if user launches app enough times to ask for Survey
     *
     * @param launchCount number of app launches
     *
     * @return if Ask For Survey dialog is opened
     */
    fun openAskForSurveyDialogIfNeeded(launchCount: Int): Boolean

    /**
     * Checks if user launches app enough times to ask for Survey
     *
     * @param launchCount number of app launches
     *
     * @return if Ask For Survey dialog should be opened
     * */
    fun shouldOpenAskFragment(launchCount: Int): Boolean

    companion object {
        const val NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG = 6
    }

    /**
     * Launches Survey in WebView implemented in JS
     *
     * @param webView for showing survey content
     * @param appVersionName current app release version name, use [BuildConfig.VERSION_NAME] for it
     * @param onNextAction callback with [SurveyEvent]s from survey
     *
     * @see <a href="https://github.com/netigenkluzowicz/apis_strapi/blob/develop/documentation/webview-survey.md">Webview survey</a>
     */
    fun showSurvey(webView: WebView, appVersionName: String, onNextAction: (surveyEvent: SurveyEvent) -> Unit)
    fun openAskForSurveyDialog()
}
