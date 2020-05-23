---
title: AdMobBanner - gms
---

[gms](../../index.md) / [pl.netigen.gms.ads](../index.md) / [AdMobBanner](./index.md)

# AdMobBanner

`class AdMobBanner : IBannerAd, `[`LifecycleObserver`](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/LifecycleObserver.html)

[IBannerAd](#) implementation with [AdView](#) from Google Mobile Ads SDK

See: [Banner Ads](https://developers.google.com/admob/android/banner)

### Constructors

| [&lt;init&gt;](-init-.html) | [IBannerAd](#) implementation with [AdView](#) from Google Mobile Ads SDK`AdMobBanner(activity: `[`ComponentActivity`](https://netigenkluzowicz.github.io/api_android/core/androidx/activity/ComponentActivity.html)`, adMobRequest: `[`IAdMobRequest`](../-i-ad-mob-request/index.md)`, adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, isAdaptiveBanner: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true)` |

### Properties

| [adId](ad-id.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) identifier`val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [enabled](enabled.html) | Indicates is current ad active`var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [getHeightInPixels](get-height-in-pixels.html) | `fun getHeightInPixels(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |

