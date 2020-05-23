---
title: AppConfig - core
---

[core](../../index.md) / [pl.netigen.core.config](../index.md) / [AppConfig](./index.md)

# AppConfig

`open class AppConfig : IAppConfig`

[IAppConfig](#) implementation which provides also [marketLinkPrefix](market-link-prefix.md)) for linking Application to current [Store](#)

### Constructors

| [&lt;init&gt;](-init-.md)) | [IAppConfig](#) implementation which provides also [marketLinkPrefix](market-link-prefix.md)) for linking Application to current [Store](#)`AppConfig(bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)` = "", store: Store, bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)` = "adsLayout", isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true, testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`> = emptyList(), inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = false, adMobPublisherIds: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`> = arrayOf(NETIGEN_ADMOB_PUBLISHER_ID), isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true, maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md)` = DEFAULT_MAX_CONSENT_WAIT_TIME_MS, maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md)` = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS, useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true)` |

### Properties

| [adMobPublisherIds](ad-mob-publisher-ids.md)) | `open val adMobPublisherIds: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |
| [bannerAdId](banner-ad-id.md)) | `open val bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [bannerLayoutIdName](banner-layout-id-name.md)) | `open val bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [inDebugMode](in-debug-mode.md)) | `open val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [interstitialAdId](interstitial-ad-id.md)) | `open val interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [isBannerAdaptive](is-banner-adaptive.md)) | `open val isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [isNoAdsAvailable](is-no-ads-available.md)) | `open val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [maxConsentWaitTime](max-consent-wait-time.md)) | `open val maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md) |
| [maxInterstitialWaitTime](max-interstitial-wait-time.md)) | `open val maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md) |
| [rewardedAdId](rewarded-ad-id.md)) | `open val rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [store](store.md)) | `val store: Store` |
| [testDevices](test-devices.md)) | `open val testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |
| [useDefaultRateUs](use-default-rate-us.md)) | `open val useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Companion Object Properties

| [marketLinkPrefix](market-link-prefix.md)) | Used for linking Application to current [Store](#), as in [Utils.openMarketLink()](../../pl.netigen.core.utils/-utils/open-market-link.md))`lateinit var marketLinkPrefix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [NETIGEN_ADMOB_PUBLISHER_ID](-n-e-t-i-g-e-n_-a-d-m-o-b_-p-u-b-l-i-s-h-e-r_-i-d.md)) | `const val NETIGEN_ADMOB_PUBLISHER_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |

