package com.netigen.sampleapp.feature.home

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.UNSET
import com.netigen.sampleapp.R
import com.netigen.sampleapp.feature.splash.SampleSplashFragment
import kotlinx.android.synthetic.main.activity_sample_main.*
import pl.netigen.core.gdpr.IGDPRConsentImpl
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreMainVMImpl
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.SplashFragment
import pl.netigen.core.splash.SplashVMImpl
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.observe
import pl.netigen.gms.ads.AdMobAds
import pl.netigen.gms.payments.GMSPayments
import timber.log.Timber.d


class SampleMainActivity : CoreMainActivity() {
    private val gdprConsent: IGDPRConsent by lazy { IGDPRConsentImpl(this, arrayOf("pub-4699516034931013")) }

    override val ads by lazy {
        AdMobAds(
            activity = this,
            bannerAdId = "",
            interstitialAdId = "",
            bannerRelativeLayout = findViewById(R.id.adsLayout),
            isAdaptiveBanner = true,
            isInDebugMode = true
        )
    }

    val splashVM: SplashVM by lazy {
        SplashVMImpl(
            gdprConsent = gdprConsent,
            ads = ads,
            noAdsPurchases = gmsPayments,
            networkStatus = NetworkStatus(this),
            isNoAdsAvailable = true
        )
    }

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

    private val gmsPayments: GMSPayments by lazy { GMSPayments(application) }
    override val viewModel: CoreMainVM by lazy { CoreMainVMImpl(ads, gmsPayments) }
    override val splashFragment: SplashFragment by lazy { SampleSplashFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)

        splashVM.splashState.observe(this) {
            d("splashState.observe: $it")
        }
        splashVM.start()
    }
}

