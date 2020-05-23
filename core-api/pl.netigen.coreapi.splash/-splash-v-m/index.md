---
title: SplashVM - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.splash](../index.html) / [SplashVM](./index.html)

# SplashVM

`abstract class SplashVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ISplashVM`](../-i-splash-v-m/index.html)

Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:

* billing if available and no-ads payments, see [INoAds](../../pl.netigen.coreapi.payments/-i-no-ads/index.html)
* app configuration by [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.html) implementation,
* Gdpr Consent for ads and shows pop up to the users if necessary, see: [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)

After initialization:

* if ads are active - loads [IInterstitialAd](../../pl.netigen.coreapi.ads/-i-interstitial-ad/index.html), and show it to the user, and after closes Splash
* if ads are disabled, closes Splash

### Constructors

| [&lt;init&gt;](-init-.html) | Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:`SplashVM(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

