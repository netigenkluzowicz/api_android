---
title: AppConfig - core
---

[core](../../index.html) / [pl.netigen.core.config](../index.html) / [AppConfig](./index.html)

# AppConfig

`open class AppConfig : IAppConfig`

[IAppConfig](#) implementation which provides also [marketLinkPrefix](market-link-prefix.html) for linking Application to current [Store](#)

### Constructors

| [&lt;init&gt;](-init-.html) | [IAppConfig](#) implementation which provides also [marketLinkPrefix](market-link-prefix.html) for linking Application to current [Store](#)`AppConfig(bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", store: Store, bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "adsLayout", isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = emptyList(), inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, adMobPublisherIds: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = arrayOf(NETIGEN_ADMOB_PUBLISHER_ID), isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_MAX_CONSENT_WAIT_TIME_MS, maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS, useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true)` |

### Properties

| [adMobPublisherIds](ad-mob-publisher-ids.html) | `open val adMobPublisherIds: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [bannerAdId](banner-ad-id.html) | `open val bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [bannerLayoutIdName](banner-layout-id-name.html) | `open val bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [inDebugMode](in-debug-mode.html) | `open val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [interstitialAdId](interstitial-ad-id.html) | `open val interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isBannerAdaptive](is-banner-adaptive.html) | `open val isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNoAdsAvailable](is-no-ads-available.html) | `open val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [maxConsentWaitTime](max-consent-wait-time.html) | `open val maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [maxInterstitialWaitTime](max-interstitial-wait-time.html) | `open val maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [rewardedAdId](rewarded-ad-id.html) | `open val rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [store](store.html) | `val store: Store` |
| [testDevices](test-devices.html) | `open val testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [useDefaultRateUs](use-default-rate-us.html) | `open val useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Companion Object Properties

| [marketLinkPrefix](market-link-prefix.html) | Used for linking Application to current [Store](#), as in [Utils.openMarketLink()](../../pl.netigen.core.utils/-utils/open-market-link.html)`lateinit var marketLinkPrefix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [NETIGEN_ADMOB_PUBLISHER_ID](-n-e-t-i-g-e-n_-a-d-m-o-b_-p-u-b-l-i-s-h-e-r_-i-d.html) | `const val NETIGEN_ADMOB_PUBLISHER_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

