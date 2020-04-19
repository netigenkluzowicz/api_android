package pl.netigen.sampleapp.flavour

import android.app.Application
import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.gms.ads.AdMobAds
import pl.netigen.gms.gdpr.GDPRConsentImpl
import pl.netigen.gms.payments.GMSPayments

object FlavoursConst {
    fun getPaymentsImpl(coreMainActivity: CoreMainActivity): IPayments = GMSPayments(coreMainActivity.application)
    fun getAdsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig) = AdMobAds(coreMainActivity, appConfig)
    fun getGDPRConsentImpl(application: Application, appConfig: AppConfig) = GDPRConsentImpl(application, appConfig)
}