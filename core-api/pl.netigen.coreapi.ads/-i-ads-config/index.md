---
title: IAdsConfig - core-api
---

[api_android](../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.ads](../index.md) / [IAdsConfig](./index.md)

# IAdsConfig

`interface IAdsConfig`

Keeps configuration for Ads

* ads identifiers,
* banner type and layout id
* list of test devices
* debug/release configuration

### Properties

| [bannerAdId](banner-ad-id.html) | [IBannerAd](../-i-banner-ad/index.md) ad identifier`abstract val bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [bannerLayoutIdName](banner-layout-id-name.html) | Id of [RelativeLayout](https://developer.android.com/reference/android/widget/RelativeLayout.html) for banner ad placement`abstract val bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [inDebugMode](in-debug-mode.html) | Sets debug mode on/off, when:`abstract val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [interstitialAdId](interstitial-ad-id.html) | [IInterstitialAd](../-i-interstitial-ad/index.md) ad identifier`abstract val interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [isBannerAdaptive](is-banner-adaptive.html) | When true (default value) adaptive banner instead of smart banner is used (available only in Admob)`abstract val isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [rewardedAdId](rewarded-ad-id.html) | [rewardedAdId](rewarded-ad-id.html) ad identifier,`abstract val rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [testDevices](test-devices.html) | List of test devices ids`abstract val testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |

### Companion Object Properties

| [DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS](-d-e-f-a-u-l-t_-d-e-l-a-y_-b-e-t-w-e-e-n_-i-n-t-e-r-s-t-i-t-i-a-l_-a-d-s_-m-s.html) | Minimum time after one [IInterstitialAd](../-i-interstitial-ad/index.md) ad was showed to show another ad, for default 60 seconds`const val DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md) |
| [REWARD_AD_MAX_RETRY_COUNT](-r-e-w-a-r-d_-a-d_-m-a-x_-r-e-t-r-y_-c-o-u-n-t.html) | Maximum number of reload tries of [IRewardedAd](../-i-rewarded-ad/index.md) after loading ad failure`const val REWARD_AD_MAX_RETRY_COUNT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |

### Inheritors

| [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.md) | Keeps configuration for entire Api/Application:`interface IAppConfig : `[`IAdsConfig`](./index.md)`, `[`IGDPRConsentConfig`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.md)`, `[`ISplashConfig`](../../pl.netigen.coreapi.splash/-i-splash-config/index.md) |

