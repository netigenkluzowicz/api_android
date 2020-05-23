---
title: AdMobBanner - gms
---

[gms](../../index.html) / [pl.netigen.gms.ads](../index.html) / [AdMobBanner](./index.html)

# AdMobBanner

`class AdMobBanner : IBannerAd, `[`LifecycleObserver`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleObserver.html)

[IBannerAd](#) implementation with [AdView](#) from Google Mobile Ads SDK

See: [Banner Ads](https://developers.google.com/admob/android/banner)

### Constructors

| [&lt;init&gt;](-init-.html) | [IBannerAd](#) implementation with [AdView](#) from Google Mobile Ads SDK`AdMobBanner(activity: `[`ComponentActivity`](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)`, adMobRequest: `[`IAdMobRequest`](../-i-ad-mob-request/index.html)`, adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isAdaptiveBanner: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true)` |

### Properties

| [adId](ad-id.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) identifier`val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [enabled](enabled.html) | Indicates is current ad active`var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [getHeightInPixels](get-height-in-pixels.html) | `fun getHeightInPixels(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

