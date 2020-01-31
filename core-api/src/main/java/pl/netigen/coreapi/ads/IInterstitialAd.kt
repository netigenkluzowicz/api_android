package pl.netigen.coreapi.ads

import kotlinx.coroutines.flow.Flow

interface IInterstitialAd : IAd {
    val isLoaded: Boolean
    fun load(): Flow<Boolean>
    fun showIfCanBeShowed(forceShow: Boolean = false, onClosedOrNotShowed: (Boolean) -> Unit)
    fun loadIfShouldBeLoaded()
}