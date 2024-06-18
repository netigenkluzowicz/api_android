package pl.netigen.coreapi.rateus

import pl.netigen.coreapi.main.Store

/**
 * Rate us module is used for show user dialog to encourage him to rate application in store and/or write application review
 *
 */
interface IRateUs {
    /**
     * Checks how many user uses app [openingCounter], and shows Rate Us dialog when this
     *
     * @return if Rate Us dialog should be showed
     */
    fun shouldOpenRateUs(): Boolean

    /**
     * Saves info that user don't wants to see Rate Us dialog again
     *
     */
    fun doNotShowRateUsAgain()

    /**
     * Checks if [shouldOpenRateUs] and if it is true shows Rate Us dialog
     *
     * @return if Rate Us dialog is opened
     */
    fun openRateDialogIfNeeded(): Boolean

    /**
     * Shows Rate Us dialog
     *
     */
    fun openOurRateDialog()

    /**
     * Called on click in dialog
     *
     * Directs user to current [Store], with intention to rate application
     *
     *
     */
    fun clickYes()

    /**
     * Called on click in dialog, after that rate us dialog will not be showed again
     *
     */
    fun clickNo()

    /**
     * Called on click in dialog, after that [openingCounter] is reset, and dialog will be showed later again
     *
     */
    fun clickLater()
    fun openGmsRateDialog()
}
