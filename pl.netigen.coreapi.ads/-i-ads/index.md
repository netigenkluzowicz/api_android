---
title: IAds - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IAds](./index.html)

# IAds

`interface IAds`

Universal interface for managing ads, such as:

* initializing ads SDk
* displaying and loading [IBannerAd](../-i-banner-ad/index.html), [IInterstitialAd](../-i-interstitial-ad/index.html) and [IRewardedAd](../-i-rewarded-ad/index.html)
* turning on/off personalized ads
* enabling and disabling all ads
* provides time minimum limit between IInterstitialAd displays
* showing test/production ads

See: [Mobile Ads SDK](https://developer.huawei.com/consumer/en/monetize)

See: [HUAWEI Ads SDK](https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/ads-sdk-guide)

### Properties

| [bannerAd](banner-ad.html) | Provides [IBannerAd](../-i-banner-ad/index.html)`abstract val bannerAd: `[`IBannerAd`](../-i-banner-ad/index.html) |
| [interstitialAd](interstitial-ad.html) | Provides [IInterstitialAd](../-i-interstitial-ad/index.html)`abstract val interstitialAd: `[`IInterstitialAd`](../-i-interstitial-ad/index.html) |
| [personalizedAdsEnabled](personalized-ads-enabled.html) | Set this to turn off/on personalized ads`abstract var personalizedAdsEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [rewardedAd](rewarded-ad.html) | Provides [IRewardedAd](../-i-rewarded-ad/index.html)`abstract val rewardedAd: `[`IRewardedAd`](../-i-rewarded-ad/index.html) |

### Functions

| [disable](disable.html) | Disable all ads, hide banners, cancel and/or stop loading ads`abstract fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [enable](enable.html) | Enables ads, starts loading and showing them`abstract fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [AdMobAds](../../pl.netigen.gms.ads/-ad-mob-ads/index.html) | [IAds](./index.html) implementation with Google Mobile Ads SDK`class AdMobAds : `[`IAds`](./index.html)`, `[`IAdMobRequest`](../../pl.netigen.gms.ads/-i-ad-mob-request/index.html) |
| [CoreMainVmImpl](../../pl.netigen.core.main/-core-main-vm-impl/index.html) | Current [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) implementation, provided implementations should be singletons`class CoreMainVmImpl : `[`CoreMainVM`](../../pl.netigen.coreapi.main/-core-main-v-m/index.html)`, `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](./index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |
| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](./index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |

