---
title: ISplashVM - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.splash](../index.md) / [ISplashVM](./index.md)

# ISplashVM

`interface ISplashVM : `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md)

Its used for Splash [AndroidViewModel](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:

* billing if available and no-ads payments, see [INoAds](../../pl.netigen.coreapi.payments/-i-no-ads/index.md)
* app configuration by [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.md) implementation,
* Gdpr Consent for ads and shows pop up to the users if necessary, see: [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)

After initialization:

* if ads are active - loads [IInterstitialAd](../../pl.netigen.coreapi.ads/-i-interstitial-ad/index.md), and show it to the user, and after closes Splash
* if ads are disabled, closes Splash

### Properties

| [gdprConsent](gdpr-consent.html) | `abstract val gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md) |
| [isFirstLaunch](is-first-launch.html) | `abstract val isFirstLaunch: `[`LiveData`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/LiveData.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [splashState](splash-state.html) | `abstract val splashState: `[`LiveData`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/LiveData.html)`<`[`SplashState`](../-splash-state/index.md)`>` |

### Functions

| [setPersonalizedAds](set-personalized-ads.html) | `abstract fun setPersonalizedAds(personalizedAdsApproved: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [start](start.html) | `abstract fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [SplashVM](../-splash-v-m/index.md) | Its used for Splash [AndroidViewModel](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:`abstract class SplashVM : `[`AndroidViewModel`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/AndroidViewModel.html)`, `[`ISplashVM`](./index.md) |

