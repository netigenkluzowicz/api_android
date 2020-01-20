package pl.netigen.core.main

import androidx.appcompat.app.AppCompatActivity
import pl.netigen.core.splash.SplashFragment
import pl.netigen.coreapi.main.MainVM

abstract class CoreMainActivity : AppCompatActivity() {
    abstract val viewModel: MainVM
    abstract val splashFragment: SplashFragment
}