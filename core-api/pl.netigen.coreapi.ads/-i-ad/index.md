---
title: IAd - core-api
---

[home page](../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.ads](../index.md) / [IAd](./index.md)

# IAd

`interface IAd`

Contains properties common for all ads types (enabled/disabled state) and ad identifier

### Properties

| [adId](ad-id.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) identifier`abstract val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [enabled](enabled.html) | Indicates is current ad enabled`abstract var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Inheritors

| [IBannerAd](../-i-banner-ad/index.md) | Manages Banner ad:`interface IBannerAd : `[`IAd`](./index.md) |
| [IInterstitialAd](../-i-interstitial-ad/index.md) | Manages Interstitial Ad:`interface IInterstitialAd : `[`IAd`](./index.md) |
| [IRewardedAd](../-i-rewarded-ad/index.md) | Manages rewarded ads:`interface IRewardedAd : `[`IAd`](./index.md) |

