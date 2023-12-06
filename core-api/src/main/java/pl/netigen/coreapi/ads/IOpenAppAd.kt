package pl.netigen.coreapi.ads

/**
 * Manages Open App Ad:
 * - loading
 * - showing to the user
 * - provides loading, showing and closing events
 *
 * App open ads are a special ad format intended for publishers wishing to monetize their app load screens.
 * App open ads can be closed at any time, and are designed to be shown when your users bring your app to the foreground.
 *
 */
interface IOpenAppAd : IAd {
    /**
     * Indicates if ad is loaded and ready to show
     */
    val isLoadedAndValid: Boolean

    /**
     * Shows ad if it is loaded
     *
     * @param onClosedOrNotShowed Callback called when ad can't be showed or ad is closed
     */
    fun showIfCanBeShowed(onClosedOrNotShowed: (Boolean) -> Unit)

    override val yandexAdId: String
        get() = ""

    fun loadIfNeeded()

    override fun enableYandex() = Unit
}
