package pl.netigen.core.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pl.netigen.core.splash.SplashFragment
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainVM

abstract class CoreMainActivity : AppCompatActivity() {
    abstract val viewModelFactory: ViewModelProvider.Factory
    val coreMainVM: ICoreMainVM by viewModels<CoreMainVM> { viewModelFactory }
    abstract val splashFragment: SplashFragment

    abstract fun onSplashOpened()
    abstract fun onSplashClosed()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState != null) {
            coreMainVM.onSavedStateRestored(savedInstanceState)
        }
    }


}