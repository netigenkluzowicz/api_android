package com.netigen.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        override val lastKnownAdConsentStatus: AdConsentStatus
            get() = AdConsentStatus.PERSONALIZED_NON_UE

        override fun requestGDPRLocation(gdprLocationStatusListener: (result: CheckGDPRLocationStatus) -> Unit) {}

        override fun saveAdConsentStatus(adConsentStatus: AdConsentStatus) {}

        override fun cancelRequest() {}
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
            networkStatus = NetworkStatus(this),
            noAdsPurchases = NoAdsNotAvailable()
        )
        splashVM.splashState.observe(this) {
            Log.d("wrobel", "splashState.observe: $it")
        }
        splashVM.onStart()

    }
}
