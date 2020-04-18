package pl.netigen.coreapi.splash

import androidx.lifecycle.LiveData
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.payments.INoAds

interface ISplashVM : INoAds {
    val gdprConsent: IGDPRConsent
    val splashState: LiveData<SplashState>
    val isFirstLaunch: LiveData<Boolean>
    val isNoAdsAvailable: Boolean
    fun setPersonalizedAds(personalizedAdsApproved: Boolean)
    fun start()
}