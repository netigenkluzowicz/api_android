---
title: IAds - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IAds](./index.html)

# IAds

`interface IAds`

### Properties

| [bannerAd](banner-ad.html) | `abstract val bannerAd: `[`IBannerAd`](../-i-banner-ad/index.html) |
| [interstitialAd](interstitial-ad.html) | `abstract val interstitialAd: `[`IInterstitialAd`](../-i-interstitial-ad/index.html) |
| [personalizedAdsEnabled](personalized-ads-enabled.html) | `abstract var personalizedAdsEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [rewardedAd](rewarded-ad.html) | `abstract val rewardedAd: `[`IRewardedAd`](../-i-rewarded-ad/index.html) |

### Functions

| [disable](disable.html) | `abstract fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [enable](enable.html) | `abstract fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) | `interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](./index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |

