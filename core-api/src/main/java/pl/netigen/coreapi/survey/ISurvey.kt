package pl.netigen.coreapi.survey

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
     * Checks if user launches app enough times to ask for Survey
     *
     * @param launchCount number of app launches
     *
     * @return if Ask For Survey dialog is opened
     */
    fun openAskForSurveyDialogIfNeeded(launchCount: Int): Boolean

    /**
     *
     * Shows Survey dialog
     *
     */
    fun openSurveyDialog()

}