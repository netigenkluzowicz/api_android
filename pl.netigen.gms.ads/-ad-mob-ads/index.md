---
title: AdMobAds - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.ads](../index.html) / [AdMobAds](./index.html)

# AdMobAds

`class AdMobAds : `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`IAdMobRequest`](../-i-ad-mob-request/index.html)

[IAds](../../pl.netigen.coreapi.ads/-i-ads/index.html) implementation with Google Mobile Ads SDK

See: [Mobile Ads SDK](https://developers.google.com/admob/android/quick-start)

### Constructors

| [&lt;init&gt;](-init-.html) | Initializes SDK, creates ads and sets up test devices`AdMobAds(activity: `[`ComponentActivity`](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)`, adsConfig: `[`IAdsConfig`](../../pl.netigen.coreapi.ads/-i-ads-config/index.html)`)` |

### Properties

| [bannerAd](banner-ad.html) | Provides [IBannerAd](../../pl.netigen.coreapi.ads/-i-banner-ad/index.html)`val bannerAd: `[`IBannerAd`](../../pl.netigen.coreapi.ads/-i-banner-ad/index.html) |
| [interstitialAd](interstitial-ad.html) | Provides [IInterstitialAd](../../pl.netigen.coreapi.ads/-i-interstitial-ad/index.html)`val interstitialAd: `[`IInterstitialAd`](../../pl.netigen.coreapi.ads/-i-interstitial-ad/index.html) |
| [personalizedAdsEnabled](personalized-ads-enabled.html) | Set this to turn off/on personalized ads`var personalizedAdsEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [rewardedAd](rewarded-ad.html) | Provides [IRewardedAd](../../pl.netigen.coreapi.ads/-i-rewarded-ad/index.html)`val rewardedAd: `[`IRewardedAd`](../../pl.netigen.coreapi.ads/-i-rewarded-ad/index.html) |

### Functions

| [disable](disable.html) | Disable all ads, hide banners, cancel and/or stop loading ads`fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [enable](enable.html) | Enables ads, starts loading and showing them`fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getAdRequest](get-ad-request.html) | `fun getAdRequest(): AdRequest` |

### Companion Object Properties

| [TEST_BANNER_ID](-t-e-s-t_-b-a-n-n-e-r_-i-d.html) | `const val TEST_BANNER_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [TEST_INTERSTITIAL_ID](-t-e-s-t_-i-n-t-e-r-s-t-i-t-i-a-l_-i-d.html) | `const val TEST_INTERSTITIAL_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [TEST_REWARDED_ID](-t-e-s-t_-r-e-w-a-r-d-e-d_-i-d.html) | `const val TEST_REWARDED_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

