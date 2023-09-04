package pl.netigen.coreapi.ads

/**
 * Contains properties common for all ads types (enabled/disabled state) and ad identifier
 *
 */
interface IAd {

    fun enableYandex()

    /**
     * Indicates is current ad enabled
     */
    var enabled: Boolean

    /**
     * Current ad [String] identifier
     */
    val adId: String
    val yandexAdId: String
}
