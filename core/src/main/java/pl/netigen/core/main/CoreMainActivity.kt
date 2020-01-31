package pl.netigen.core.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import pl.netigen.core.splash.SplashFragment
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainVM
import pl.netigen.extensions.observe

abstract class CoreMainActivity : AppCompatActivity() {
    private var noAdsActive: Boolean = false
    private var splashActive: Boolean = false
    abstract val viewModelFactory: ViewModelProvider.Factory
    val coreMainVM: ICoreMainVM by viewModels<CoreMainVM> { viewModelFactory }
    abstract val splashFragment: SplashFragment

    open fun onSplashOpened() {
        splashActive = true
        hideAds()
    }

    open fun onSplashClosed() {
        splashActive = false
        onNoAdsChanged(noAdsActive)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coreMainVM.noAdsActive.asLiveData().observe(this, this::onNoAdsChanged)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        coreMainVM.start()
    }

    open fun onNoAdsChanged(noAdsActive: Boolean) {
        this.noAdsActive = noAdsActive
        if (!splashActive) if (noAdsActive) hideAds() else showAds()
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            coreMainVM.onActivityResult(requestCode, resultCode, data)
        }
    }

    abstract fun hideAds()
    abstract fun showAds()
}