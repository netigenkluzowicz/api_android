package pl.netigen.coreapi.main

import android.content.Intent
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider

interface ICoreMainActivity {
    val canCommitFragments: Boolean
    val noAdsActive: Boolean
    val splashActive: Boolean
    val viewModelFactory: ViewModelProvider.Factory
    val coreMainVM: ICoreMainVM

    fun onSplashOpened()

    fun onSplashClosed()

    fun showGdprPopUp()

    @CallSuper
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    fun hideAds()
    fun showAds()
    fun onNoAdsChanged(noAdsActive: Boolean)
}