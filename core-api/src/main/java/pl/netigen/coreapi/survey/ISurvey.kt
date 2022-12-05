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
        const val BASE_URL = "https://feedback.netigen.eu/survey/"
        const val FORCE_SHOW = -100
        const val MIN_SURVEY_TEXTS_LENGTH = 4
        const val NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG = 6
    }
}
