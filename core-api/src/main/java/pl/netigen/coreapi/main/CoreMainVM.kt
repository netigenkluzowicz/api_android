package pl.netigen.coreapi.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.payments.INoAds

abstract class CoreMainVM : ViewModel(), INoAds, IAds {
    abstract fun onSavedStateRestored(savedInstanceState: Bundle)
}