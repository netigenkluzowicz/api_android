package pl.netigen.coreapi.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.payments.INoAds

abstract class MainVM : ViewModel(), INoAds, IInterstitialAd {
    abstract val showSplash: LiveData<Boolean>
}