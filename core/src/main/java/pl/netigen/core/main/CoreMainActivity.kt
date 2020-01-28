package pl.netigen.core.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pl.netigen.core.config.AppConfig
import pl.netigen.core.splash.SplashFragment
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainVM
import pl.netigen.coreapi.payments.IPayments

abstract class CoreMainActivity : AppCompatActivity() {
    open val viewModelFactory: ViewModelProvider.Factory
        get() = CoreViewModelsFactory(this, appConfig, payments)
    val viewModel: ICoreMainVM by viewModels<CoreMainVM> { viewModelFactory }
    abstract val splashFragment: SplashFragment
    abstract val appConfig: AppConfig
    abstract val payments: IPayments

    abstract fun onSplashOpened()
    abstract fun onSplashClosed()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState != null) {
            viewModel.onSavedStateRestored(savedInstanceState)
        }
    }


}