---
title: ISplashVM - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.splash](../index.html) / [ISplashVM](./index.html)

# ISplashVM

`interface ISplashVM : `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html)

Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:

* billing if available and no-ads payments, see [INoAds](../../pl.netigen.coreapi.payments/-i-no-ads/index.html)
* app configuration by [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.html) implementation,
* Gdpr Consent for ads and shows pop up to the users if necessary, see: [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)

After initialization:

* if ads are active - loads [IInterstitialAd](../../pl.netigen.coreapi.ads/-i-interstitial-ad/index.html), and show it to the user, and after closes Splash
* if ads are disabled, closes Splash

### Properties

| [gdprConsent](gdpr-consent.html) | `abstract val gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html) |
| [isFirstLaunch](is-first-launch.html) | `abstract val isFirstLaunch: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [splashState](splash-state.html) | `abstract val splashState: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`SplashState`](../-splash-state/index.html)`>` |

### Functions

| [setPersonalizedAds](set-personalized-ads.html) | `abstract fun setPersonalizedAds(personalizedAdsApproved: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.html) | `abstract fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [SplashVM](../-splash-v-m/index.html) | Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:`abstract class SplashVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ISplashVM`](./index.html) |

