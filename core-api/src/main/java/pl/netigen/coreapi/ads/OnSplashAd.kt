package pl.netigen.coreapi.ads

interface OnSplashAd {
    /**
     * Loads ad and provides load success/failure callback
     *
     * @param onLoadSuccess Callback called when ad is loaded or an error occurred
     */
    fun load(onLoadSuccess: (Boolean) -> Unit)

    /**
     * Shows ad if it is loaded and minimum time between this ads passed (limit can be ignored by [forceShow] = true)
     *
     * @param forceShow If true time limit is ignored
     * @param onClosedOrNotShowed Callback called when ad can't be showed or ad is closed
     */
    fun showIfCanBeShowed(forceShow: Boolean = false, onClosedOrNotShowed: (Boolean) -> Unit)
}
