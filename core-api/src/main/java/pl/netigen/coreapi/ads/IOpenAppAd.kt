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
interface IOpenAppAd : OnSplashAd, IAd {
    val active: Boolean
        get() = adId.isNotEmpty()

    /**
     * Indicates if ad is loaded and ready to show
     */
    val isLoadedAndValid: Boolean

    override val yandexAdId: String
        get() = ""

    override fun enableYandex() = Unit
}
