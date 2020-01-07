package pl.netigen.coreapi.ads

interface IAd {
    var enabled: Boolean
    val adId: AdId<*>
}