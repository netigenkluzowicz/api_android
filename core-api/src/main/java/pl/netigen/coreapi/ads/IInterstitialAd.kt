package pl.netigen.coreapi.ads

import kotlinx.coroutines.flow.Flow

interface IInterstitialAd : IAd {
    fun loadInterstitialAd() : Flow<Boolean>
    fun showInterstitialAd(onClosedOrNotShowed: (Boolean) -> Unit)
}