---
title: AppConfig - api_android
---

[api_android](../../index.html) / [pl.netigen.core.config](../index.html) / [AppConfig](./index.html)

# AppConfig

`open class AppConfig : `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html)

[IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.html) implementation which provides also [marketLinkPrefix](market-link-prefix.html) for linking Application to current [Store](../../pl.netigen.coreapi.main/-store/index.html)

### Constructors

| [&lt;init&gt;](-init-.html) | [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.html) implementation which provides also [marketLinkPrefix](market-link-prefix.html) for linking Application to current [Store](../../pl.netigen.coreapi.main/-store/index.html)`AppConfig(bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", store: `[`Store`](../../pl.netigen.coreapi.main/-store/index.html)`, bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "adsLayout", isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = emptyList(), inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, adMobPublisherIds: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = arrayOf(NETIGEN_ADMOB_PUBLISHER_ID), isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_MAX_CONSENT_WAIT_TIME_MS, maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS, useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true)` |

### Properties

| [adMobPublisherIds](ad-mob-publisher-ids.html) | List of Admob publisher ids`open val adMobPublisherIds: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [bannerAdId](banner-ad-id.html) | [IBannerAd](../../pl.netigen.coreapi.ads/-i-banner-ad/index.html) ad identifier`open val bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [bannerLayoutIdName](banner-layout-id-name.html) | Id of [RelativeLayout](#) for banner ad placement`open val bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [inDebugMode](in-debug-mode.html) | Sets debug mode on/off, when:`open val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [interstitialAdId](interstitial-ad-id.html) | [IInterstitialAd](../../pl.netigen.coreapi.ads/-i-interstitial-ad/index.html) ad identifier`open val interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isBannerAdaptive](is-banner-adaptive.html) | When true (default value) adaptive banner instead of smart banner is used (available only in Admob)`open val isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNoAdsAvailable](is-no-ads-available.html) | Set if there is or isn't no-ads payment in application`open val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [maxConsentWaitTime](max-consent-wait-time.html) | Max white time on first splash launch to fetch consent information from web services`open val maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [maxInterstitialWaitTime](max-interstitial-wait-time.html) | Max white time for load splash interstitial ad, it is start counting after consent operations are finished`open val maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [rewardedAdId](rewarded-ad-id.html) | [rewardedAdId](../../pl.netigen.coreapi.ads/-i-ads-config/rewarded-ad-id.html) ad identifier,`open val rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [store](store.html) | Current release target Store, one of:`val store: `[`Store`](../../pl.netigen.coreapi.main/-store/index.html) |
| [testDevices](test-devices.html) | List of test devices ids`open val testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [useDefaultRateUs](use-default-rate-us.html) | Indicates If use default ["Rate us"](../../pl.netigen.coreapi.rateus/-i-rate-us/index.html)`open val useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Companion Object Properties

| [marketLinkPrefix](market-link-prefix.html) | Used for linking Application to current [Store](../../pl.netigen.coreapi.main/-store/index.html), as in [Utils.openMarketLink()](../../pl.netigen.core.utils/-utils/open-market-link.html)`lateinit var marketLinkPrefix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [NETIGEN_ADMOB_PUBLISHER_ID](-n-e-t-i-g-e-n_-a-d-m-o-b_-p-u-b-l-i-s-h-e-r_-i-d.html) | `const val NETIGEN_ADMOB_PUBLISHER_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

