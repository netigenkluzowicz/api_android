package pl.netigen.sampleapp.feature.home

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.UNSET
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_sample_main.*
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.splash.SplashFragment
import pl.netigen.sampleapp.R
import pl.netigen.sampleapp.feature.splash.SampleSplashFragment
import timber.log.Timber

class SampleMainActivity : CoreMainActivity() {
    override val viewModelFactory: ViewModelProvider.Factory
        get() = ViewModelFactory(this)
    override val splashFragment: SplashFragment by lazy { SampleSplashFragment() }

    override fun hideAds() {
        Timber.d("()")
        val navContainer = layoutHomeContainer.view ?: return
        val layoutParams = navContainer.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = UNSET
        layoutParams.bottomToBottom = PARENT_ID
        navContainer.layoutParams = layoutParams
    }

    override fun showAds() {
        Timber.d("()")
        val navContainer = layoutHomeContainer.view ?: return
        val layoutParams = navContainer.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = adsBorder.id
        layoutParams.bottomToBottom = UNSET
        navContainer.layoutParams = layoutParams
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)
    }

}

