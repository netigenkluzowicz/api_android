package pl.netigen.core.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.netigen.core.splash.SplashFragment
import pl.netigen.coreapi.main.MainVM

abstract class CoreMainActivity : AppCompatActivity() {
    abstract val viewModel: MainVM
    abstract val splashFragment: SplashFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashFragment.show(supportFragmentManager, SPLASH_FRAGMENT_TAG)
    }

    companion object {
        const val SPLASH_FRAGMENT_TAG = "SplashFragment"
    }
}