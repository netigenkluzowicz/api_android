---
title: SplashVM.<init> - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.splash](../index.md) / [SplashVM](index.md) / [&lt;init&gt;](./-init-.html)

# &lt;init&gt;

`SplashVM(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)`

Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:

* billing if available and no-ads payments, see [INoAds](../../pl.netigen.coreapi.payments/-i-no-ads/index.md)
* app configuration by [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.md) implementation,
* Gdpr Consent for ads and shows pop up to the users if necessary, see: [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)

After initialization:

* if ads are active - loads [IInterstitialAd](../../pl.netigen.coreapi.ads/-i-interstitial-ad/index.md), and show it to the user, and after closes Splash
* if ads are disabled, closes Splash
