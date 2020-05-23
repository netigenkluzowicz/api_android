---
title: IAds - core-api
---

[home page](../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.ads](../index.md) / [IAds](./index.md)

# IAds

`interface IAds`

Universal interface for managing ads, such as:

* initializing ads SDk
* displaying and loading [IBannerAd](../-i-banner-ad/index.md), [IInterstitialAd](../-i-interstitial-ad/index.md) and [IRewardedAd](../-i-rewarded-ad/index.md)
* turning on/off personalized ads
* enabling and disabling all ads
* provides time minimum limit between IInterstitialAd displays
* showing test/production ads

See: [Mobile Ads SDK](https://developer.huawei.com/consumer/en/monetize)

See: [HUAWEI Ads SDK](https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/ads-sdk-guide)

### Properties

| [bannerAd](banner-ad.html) | Provides [IBannerAd](../-i-banner-ad/index.md)`abstract val bannerAd: `[`IBannerAd`](../-i-banner-ad/index.md) |
| [interstitialAd](interstitial-ad.html) | Provides [IInterstitialAd](../-i-interstitial-ad/index.md)`abstract val interstitialAd: `[`IInterstitialAd`](../-i-interstitial-ad/index.md) |
| [personalizedAdsEnabled](personalized-ads-enabled.html) | Set this to turn off/on personalized ads`abstract var personalizedAdsEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [rewardedAd](rewarded-ad.html) | Provides [IRewardedAd](../-i-rewarded-ad/index.md)`abstract val rewardedAd: `[`IRewardedAd`](../-i-rewarded-ad/index.md) |

### Functions

| [disable](disable.html) | Disable all ads, hide banners, cancel and/or stop loading ads`abstract fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [enable](enable.html) | Enables ads, starts loading and showing them`abstract fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.md) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](./index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md) |

