---
title: ISplashVM - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.splash](../index.md) / [ISplashVM](./index.md)

# ISplashVM

`interface ISplashVM : `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md)

Its used for Splash [AndroidViewModel](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/AndroidViewModel.md)), provides application initialization,:

* billing if available and no-ads payments, see [INoAds](../../pl.netigen.coreapi.payments/-i-no-ads/index.md)
* app configuration by [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.md) implementation,
* Gdpr Consent for ads and shows pop up to the users if necessary, see: [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)

After initialization:

* if ads are active - loads [IInterstitialAd](../../pl.netigen.coreapi.ads/-i-interstitial-ad/index.md), and show it to the user, and after closes Splash
* if ads are disabled, closes Splash

### Properties

| [gdprConsent](gdpr-consent.md)) | `abstract val gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md) |
| [isFirstLaunch](is-first-launch.md)) | `abstract val isFirstLaunch: `[`LiveData`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/LiveData.md))`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [splashState](splash-state.md)) | `abstract val splashState: `[`LiveData`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/LiveData.md))`<`[`SplashState`](../-splash-state/index.md)`>` |

### Functions

| [setPersonalizedAds](set-personalized-ads.md)) | `abstract fun setPersonalizedAds(personalizedAdsApproved: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [start](start.md)) | `abstract fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [SplashVM](../-splash-v-m/index.md) | Its used for Splash [AndroidViewModel](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/AndroidViewModel.md)), provides application initialization,:`abstract class SplashVM : `[`AndroidViewModel`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/AndroidViewModel.md))`, `[`ISplashVM`](./index.md) |

