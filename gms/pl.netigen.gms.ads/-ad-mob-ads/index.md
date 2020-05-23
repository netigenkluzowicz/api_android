---
title: AdMobAds - gms
---

[gms](../../index.md) / [pl.netigen.gms.ads](../index.md) / [AdMobAds](./index.md)

# AdMobAds

`class AdMobAds : IAds, `[`IAdMobRequest`](../-i-ad-mob-request/index.md)

[IAds](#) implementation with Google Mobile Ads SDK

See: [Mobile Ads SDK](https://developers.google.com/admob/android/quick-start)

### Constructors

| [&lt;init&gt;](-init-.html) | Initializes SDK, creates ads and sets up test devices`AdMobAds(activity: `[`ComponentActivity`](https://netigenkluzowicz.github.io/api_android/core/androidx/activity/ComponentActivity.html)`, adsConfig: IAdsConfig)` |

### Properties

| [bannerAd](banner-ad.html) | `val bannerAd: IBannerAd` |
| [interstitialAd](interstitial-ad.html) | `val interstitialAd: IInterstitialAd` |
| [personalizedAdsEnabled](personalized-ads-enabled.html) | `var personalizedAdsEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [rewardedAd](rewarded-ad.html) | `val rewardedAd: IRewardedAd` |

### Functions

| [disable](disable.html) | `fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [enable](enable.html) | `fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [getAdRequest](get-ad-request.html) | `fun getAdRequest(): AdRequest` |

### Companion Object Properties

| [TEST_BANNER_ID](-t-e-s-t_-b-a-n-n-e-r_-i-d.html) | `const val TEST_BANNER_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [TEST_INTERSTITIAL_ID](-t-e-s-t_-i-n-t-e-r-s-t-i-t-i-a-l_-i-d.html) | `const val TEST_INTERSTITIAL_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [TEST_REWARDED_ID](-t-e-s-t_-r-e-w-a-r-d-e-d_-i-d.html) | `const val TEST_REWARDED_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |

