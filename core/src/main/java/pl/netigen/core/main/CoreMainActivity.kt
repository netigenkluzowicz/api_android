package pl.netigen.core.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.netigen.core.splash.SplashFragment
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.main.CoreMainVM

abstract class CoreMainActivity : AppCompatActivity() {
    abstract val viewModel: CoreMainVM
    abstract val splashFragment: SplashFragment
    abstract val ads: IAds

    abstract fun onSplashOpened()
    abstract fun onSplashClosed()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState != null) {
            viewModel.onSavedStateRestored()
        }
    }
}