---
title: alltypes - core-api
---

### All Types

|

##### [pl.netigen.coreapi.gdpr.AdConsentStatus](../pl.netigen.coreapi.gdpr/-ad-consent-status/index.html)

Possible values of Gdpr ads consent status


|

##### [pl.netigen.utils.BaseDialogFragment](../pl.netigen.utils/-base-dialog-fragment/index.html)


|

##### [pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus](../pl.netigen.coreapi.gdpr/-check-g-d-p-r-location-status/index.html)

Possible user GDPR location statuses:


|

##### [pl.netigen.coreapi.main.CoreMainVM](../pl.netigen.coreapi.main/-core-main-v-m/index.html)

Base class for [ICoreMainVM](../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) implementation, extends [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) because it must be available from any Fragment or Activity


| (extensions in package pl.netigen.extensions)

##### [androidx.fragment.app.DialogFragment](../pl.netigen.extensions/androidx.fragment.app.-dialog-fragment/index.html)


|

##### [pl.netigen.extensions.DistinctObservableProperty](../pl.netigen.extensions/-distinct-observable-property/index.html)


| (extensions in package pl.netigen.extensions)

##### [android.graphics.drawable.Drawable](../pl.netigen.extensions/android.graphics.drawable.-drawable/index.html)


| (extensions in package pl.netigen.extensions)

##### [androidx.fragment.app.Fragment](../pl.netigen.extensions/androidx.fragment.app.-fragment/index.html)


|

##### [pl.netigen.coreapi.gdpr.GDPRClickListener](../pl.netigen.coreapi.gdpr/-g-d-p-r-click-listener/index.html)


|

##### [pl.netigen.coreapi.ads.IAd](../pl.netigen.coreapi.ads/-i-ad/index.html)

Contains properties common for all ads types (enabled/disabled state) and ad identifier


|

##### [pl.netigen.coreapi.ads.IAds](../pl.netigen.coreapi.ads/-i-ads/index.html)

Universal interface for managing ads, such as:


|

##### [pl.netigen.coreapi.ads.IAdsConfig](../pl.netigen.coreapi.ads/-i-ads-config/index.html)

Keeps configuration for Ads


|

##### [pl.netigen.coreapi.main.IAppConfig](../pl.netigen.coreapi.main/-i-app-config/index.html)

Keeps configuration for entire Api/Application:


|

##### [pl.netigen.coreapi.ads.IBannerAd](../pl.netigen.coreapi.ads/-i-banner-ad/index.html)

Manages Banner ad:


|

##### [pl.netigen.coreapi.main.ICoreMainActivity](../pl.netigen.coreapi.main/-i-core-main-activity/index.html)

Base and by design should be only Activity in application:


|

##### [pl.netigen.coreapi.main.ICoreMainVM](../pl.netigen.coreapi.main/-i-core-main-v-m/index.html)

This implementations, provides access to api modules:


|

##### [pl.netigen.coreapi.gdpr.ICoreSplashFragment](../pl.netigen.coreapi.gdpr/-i-core-splash-fragment.html)

Used for Gdpr pop-up fragment, see [IGDPRConsent](../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)


|

##### [pl.netigen.coreapi.main.ICoreViewModelsFactory](../pl.netigen.coreapi.main/-i-core-view-models-factory/index.html)

Interface used for provide [ViewModelProvider.Factory](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider/Factory.html) to instantiate [ICoreMainVM](../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) and [ISplashVM](../pl.netigen.coreapi.splash/-i-splash-v-m/index.html) implementations


|

##### [pl.netigen.coreapi.gdpr.IGDPRConsent](../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)

Its used for collect user consent for displaying ads, and ask them if we can provide personalized ads


|

##### [pl.netigen.coreapi.gdpr.IGDPRConsentConfig](../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.html)

Configurations for Gdpr Consent, as for now only List of Admob publisher ids is needed


|

##### [pl.netigen.coreapi.gdpr.IGDPRTexts](../pl.netigen.coreapi.gdpr/-i-g-d-p-r-texts/index.html)

List of texts for Consent PopUp
See implementations for more info


|

##### [pl.netigen.coreapi.ads.IInterstitialAd](../pl.netigen.coreapi.ads/-i-interstitial-ad/index.html)

Manages Interstitial Ad:


| (extensions in package pl.netigen.extensions)

##### [android.widget.ImageView](../pl.netigen.extensions/android.widget.-image-view/index.html)


|

##### [pl.netigen.coreapi.network.INetworkStatus](../pl.netigen.coreapi.network/-i-network-status/index.html)


|

##### [pl.netigen.coreapi.payments.INoAds](../pl.netigen.coreapi.payments/-i-no-ads/index.html)

Interface for no ads Payments, which turns on/off ads in whole application


| (extensions in package pl.netigen.extensions)

##### [kotlin.Int](../pl.netigen.extensions/kotlin.-int/index.html)


|

##### [pl.netigen.coreapi.payments.IPayments](../pl.netigen.coreapi.payments/-i-payments/index.html)

Interface for payments, extends [INoAds](../pl.netigen.coreapi.payments/-i-no-ads/index.html)


|

##### [pl.netigen.coreapi.payments.IPaymentsRepo](../pl.netigen.coreapi.payments/-i-payments-repo/index.html)

Interface for current payments repository implementation


|

##### [pl.netigen.coreapi.rateus.IRateUs](../pl.netigen.coreapi.rateus/-i-rate-us/index.html)

Rate us module is used for show user dialog to encourage him to rate application in store and/or write application review


|

##### [pl.netigen.coreapi.ads.IRewardedAd](../pl.netigen.coreapi.ads/-i-rewarded-ad/index.html)

Manages rewarded ads:


|

##### [pl.netigen.coreapi.splash.ISplashConfig](../pl.netigen.coreapi.splash/-i-splash-config/index.html)

Base Splash configuration


|

##### [pl.netigen.coreapi.splash.ISplashTimer](../pl.netigen.coreapi.splash/-i-splash-timer/index.html)

Timer utils for [CoreSplashVMImpl](#)


|

##### [pl.netigen.coreapi.splash.ISplashVM](../pl.netigen.coreapi.splash/-i-splash-v-m/index.html)

Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:


| (extensions in package pl.netigen.extensions)

##### [kotlin.collections.Iterable](../pl.netigen.extensions/kotlin.collections.-iterable/index.html)


| (extensions in package pl.netigen.extensions)

##### [androidx.lifecycle.LiveData](../pl.netigen.extensions/androidx.lifecycle.-live-data/index.html)


|

##### [pl.netigen.extensions.MutableSingleLiveEvent](../pl.netigen.extensions/-mutable-single-live-event/index.html)


| (extensions in package pl.netigen.extensions)

##### [androidx.navigation.NavController](../pl.netigen.extensions/androidx.navigation.-nav-controller/index.html)


|

##### [pl.netigen.coreapi.payments.model.NetigenSkuDetails](../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)

Represents an in-app product's or subscription's  details.


|

##### [pl.netigen.coreapi.network.NetworkStatusChangeListener](../pl.netigen.coreapi.network/-network-status-change-listener/index.html)


|

##### [pl.netigen.coreapi.payments.NoAdsNotAvailable](../pl.netigen.coreapi.payments/-no-ads-not-available/index.html)

[INoAds](../pl.netigen.coreapi.payments/-i-no-ads/index.html) implementation when we have no no-ads payment


| (extensions in package pl.netigen.extensions)

##### [kotlin.Pair](../pl.netigen.extensions/kotlin.-pair/index.html)


|

##### [pl.netigen.coreapi.payments.model.PaymentError](../pl.netigen.coreapi.payments.model/-payment-error/index.html)

Represents payment or billing service error with message and error type


|

##### [pl.netigen.coreapi.payments.model.PaymentErrorType](../pl.netigen.coreapi.payments.model/-payment-error-type/index.html)

Enumerates possible billing flow errors


|

##### [pl.netigen.coreapi.payments.model.PaymentEvent](../pl.netigen.coreapi.payments.model/-payment-event.html)

Represents Payment state it can be [PaymentSuccess](../pl.netigen.coreapi.payments.model/-payment-success/index.html), [PaymentRestored](../pl.netigen.coreapi.payments.model/-payment-restored/index.html) or [Error](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.html)


|

##### [pl.netigen.coreapi.payments.model.PaymentRestored](../pl.netigen.coreapi.payments.model/-payment-restored/index.html)

Represents payment restored from billing service with given sku(product Id)


|

##### [pl.netigen.coreapi.payments.Payments](../pl.netigen.coreapi.payments/-payments/index.html)

Base class for [IPayments](../pl.netigen.coreapi.payments/-i-payments/index.html) implementations


|

##### [pl.netigen.coreapi.payments.model.PaymentSuccess](../pl.netigen.coreapi.payments.model/-payment-success/index.html)

Represents successful payment done by user with given sku(product Id)


|

##### [pl.netigen.coreapi.payments.Security](../pl.netigen.coreapi.payments/-security/index.html)


| (extensions in package pl.netigen.extensions)

##### [android.widget.SeekBar](../pl.netigen.extensions/android.widget.-seek-bar/index.html)


|

##### [pl.netigen.extensions.SingleLiveEvent](../pl.netigen.extensions/-single-live-event/index.html)


|

##### [pl.netigen.coreapi.splash.SplashState](../pl.netigen.coreapi.splash/-splash-state/index.html)


|

##### [pl.netigen.coreapi.splash.SplashVM](../pl.netigen.coreapi.splash/-splash-v-m/index.html)

Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:


|

##### [pl.netigen.coreapi.main.Store](../pl.netigen.coreapi.main/-store/index.html)

Possible target Stores for Application release


| (extensions in package pl.netigen.extensions)

##### [kotlin.String](../pl.netigen.extensions/kotlin.-string/index.html)


| (extensions in package pl.netigen.extensions)

##### [android.view.View](../pl.netigen.extensions/android.view.-view/index.html)


| (extensions in package pl.netigen.extensions)

##### [androidx.lifecycle.ViewModel](../pl.netigen.extensions/androidx.lifecycle.-view-model/index.html)


