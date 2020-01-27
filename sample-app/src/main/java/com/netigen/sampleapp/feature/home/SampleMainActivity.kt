package com.netigen.sampleapp.feature.home

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.UNSET
import com.netigen.sampleapp.R
import com.netigen.sampleapp.feature.splash.SampleSplashFragment
import kotlinx.android.synthetic.main.activity_sample_main.*
import pl.netigen.core.ads.AdMobAds
import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreMainVMImpl
import pl.netigen.core.splash.SplashFragment
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.gms.payments.GMSPayments
import timber.log.Timber.d


class SampleMainActivity : CoreMainActivity() {
    override val appConfig = AppConfig(
        bannerAdId = "",
        interstitialAdId = "",
        inDebugMode = true
    )
    override val ads by lazy {
        AdMobAds(
            activity = this,
            adsConfig = appConfig
        )
    }

    override val payments: GMSPayments by lazy { GMSPayments(application) }
    override val viewModel: CoreMainVM by lazy { CoreMainVMImpl(ads, payments) }
    override val splashFragment: SplashFragment by lazy { SampleSplashFragment() }

    override fun onSplashOpened() {
        val navContainer = layoutHomeContainer.view ?: return
        val layoutParams = navContainer.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = UNSET
        layoutParams.bottomToBottom = PARENT_ID
        navContainer.layoutParams = layoutParams
    }

    override fun onSplashClosed() {
        val navContainer = layoutHomeContainer.view ?: return
        val layoutParams = navContainer.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = adsBorder.id
        layoutParams.bottomToBottom = UNSET
        navContainer.layoutParams = layoutParams
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)
        d(savedInstanceState.toString())
    }
}

