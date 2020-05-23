---
title: IAd - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IAd](./index.html)

# IAd

`interface IAd`

Contains properties common for all ads types (enabled/disabled state) and ad identifier

### Properties

| [adId](ad-id.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) identifier`abstract val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [enabled](enabled.html) | Indicates is current ad enabled`abstract var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| [IBannerAd](../-i-banner-ad/index.html) | Manages Banner ad:`interface IBannerAd : `[`IAd`](./index.html) |
| [IInterstitialAd](../-i-interstitial-ad/index.html) | Manages Interstitial Ad:`interface IInterstitialAd : `[`IAd`](./index.html) |
| [IRewardedAd](../-i-rewarded-ad/index.html) | Manages rewarded ads:`interface IRewardedAd : `[`IAd`](./index.html) |

