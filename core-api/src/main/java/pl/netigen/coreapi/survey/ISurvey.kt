package pl.netigen.coreapi.survey

import pl.netigen.coreapi.main.Store

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
     * Called on click in dialog, after user Cancels(closes) Survey Dialog
     *
     */
    fun clickCancel()

    /**
     * Called on click in dialog,
     *
     */
    fun clickSend()

    /**
     * Checks if user launches app enough times to ask for Survey
     *
     * @return if Ask For Survey dialog is opened
     */
    fun openAskForSurveyDialogIfNeeded(): Boolean

}