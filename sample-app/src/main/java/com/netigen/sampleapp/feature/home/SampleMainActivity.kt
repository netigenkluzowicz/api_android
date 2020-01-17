package com.netigen.sampleapp.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.netigen.sampleapp.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.SplashVMImpl
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.payments.NoAdsNotAvailable
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.observe
import pl.netigen.gms.ads.AdMobAds
import timber.log.Timber.d

@ExperimentalCoroutinesApi
class SampleMainActivity : AppCompatActivity() {


    private val gdprConsent = object : IGDPRConsent {
        override val adConsentStatus: Flow<AdConsentStatus>
            get() = flow { emit(AdConsentStatus.UNINITIALIZED) }

        override fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus> = flow { emit(CheckGDPRLocationStatus.UE) }

        override fun saveAdConsentStatus(adConsentStatus: AdConsentStatus) = Unit
    }

    val ads by lazy {
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
            noAdsPurchases = NoAdsNotAvailable,
            networkStatus = NetworkStatus(this),
            isNoAdsAvailable = true
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)

        splashVM.splashState.observe(this) {
            d("splashState.observe: $it")
        }
        splashVM.onStart()
    }
}

