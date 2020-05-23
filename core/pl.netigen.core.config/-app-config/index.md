---
title: AppConfig - core
---

[api_android](../index.md)/[core](../../index.md)/[pl.netigen.core.config](../index.md)/[AppConfig](./index.md)

# AppConfig

`open class AppConfig : IAppConfig`

[IAppConfig](#) implementation which provides also [marketLinkPrefix](market-link-prefix.html) for linking Application to current [Store](#)

### Constructors

| [&lt;init&gt;](-init-.html) | [IAppConfig](#) implementation which provides also [marketLinkPrefix](market-link-prefix.html) for linking Application to current [Store](#)`AppConfig(bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)` = "", store: Store, bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)` = "adsLayout", isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true, testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`> = emptyList(), inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = false, adMobPublisherIds: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`> = arrayOf(NETIGEN_ADMOB_PUBLISHER_ID), isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true, maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md)` = DEFAULT_MAX_CONSENT_WAIT_TIME_MS, maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md)` = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS, useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true)` |

### Properties

| [adMobPublisherIds](ad-mob-publisher-ids.html) | `open val adMobPublisherIds: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |
| [bannerAdId](banner-ad-id.html) | `open val bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [bannerLayoutIdName](banner-layout-id-name.html) | `open val bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [inDebugMode](in-debug-mode.html) | `open val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [interstitialAdId](interstitial-ad-id.html) | `open val interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [isBannerAdaptive](is-banner-adaptive.html) | `open val isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [isNoAdsAvailable](is-no-ads-available.html) | `open val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [maxConsentWaitTime](max-consent-wait-time.html) | `open val maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md) |
| [maxInterstitialWaitTime](max-interstitial-wait-time.html) | `open val maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md) |
| [rewardedAdId](rewarded-ad-id.html) | `open val rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [store](store.html) | `val store: Store` |
| [testDevices](test-devices.html) | `open val testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |
| [useDefaultRateUs](use-default-rate-us.html) | `open val useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Companion Object Properties

| [marketLinkPrefix](market-link-prefix.html) | Used for linking Application to current [Store](#), as in [Utils.openMarketLink()](../../pl.netigen.core.utils/-utils/open-market-link.html)`lateinit var marketLinkPrefix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [NETIGEN_ADMOB_PUBLISHER_ID](-n-e-t-i-g-e-n_-a-d-m-o-b_-p-u-b-l-i-s-h-e-r_-i-d.html) | `const val NETIGEN_ADMOB_PUBLISHER_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |

