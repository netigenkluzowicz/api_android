package pl.netigen.core.ads

interface IAd {
    var enabled: Boolean
    val adId: AdId<*>
}