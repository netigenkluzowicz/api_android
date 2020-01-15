package com.netigen.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.SplashVM
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.payments.NoAdsNotAvailable
import pl.netigen.coreapi.splash.ISplashVM
import pl.netigen.extensions.observe
import pl.netigen.gms.ads.AdmobAds

class SampleMainActivity : AppCompatActivity() {

    private val gdprConsent = object : IGDPRConsent {
        override val adConsentStatus: Flow<AdConsentStatus>
            get() = flow { emit(AdConsentStatus.PERSONALIZED_NON_UE) }

        override fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus> = flow { emit(CheckGDPRLocationStatus.NON_UE) }

        override fun saveAdConsentStatus(adConsentStatus: AdConsentStatus) = Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)

        val ads = AdmobAds(
            activity = this,
            bannerAdId = "",
            interstitialAdId = "",
            bannerRelativeLayout = findViewById(R.id.adsLayout),
            isInDebugMode = true
        )

        val splashVM: ISplashVM = SplashVM(
            gdprConsent = gdprConsent,
            ads = ads,
            noAdsPurchases = NoAdsNotAvailable,
            networkStatus = NetworkStatus(this)
        )
        splashVM.splashState.observe(this) {
            Log.d("wrobel", "splashState.observe: $it")
        }
        splashVM.onStart()

    }
}
