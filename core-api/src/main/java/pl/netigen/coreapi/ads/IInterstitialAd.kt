package pl.netigen.coreapi.ads

import kotlinx.coroutines.flow.Flow

interface IInterstitialAd : IAd {
    val isLoaded: Boolean
    fun loadInterstitialAd(): Flow<Boolean>
    fun showInterstitialAd(forceShow: Boolean = false, onClosedOrNotShowed: (Boolean) -> Unit)
}