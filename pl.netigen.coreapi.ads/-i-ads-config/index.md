---
title: IAdsConfig - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IAdsConfig](./index.html)

# IAdsConfig

`interface IAdsConfig`

### Properties

| [bannerAdId](banner-ad-id.html) | `abstract val bannerAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [bannerLayoutIdName](banner-layout-id-name.html) | `abstract val bannerLayoutIdName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [inDebugMode](in-debug-mode.html) | `abstract val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [interstitialAdId](interstitial-ad-id.html) | `abstract val interstitialAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isBannerAdaptive](is-banner-adaptive.html) | `abstract val isBannerAdaptive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [rewardedAdId](rewarded-ad-id.html) | `abstract val rewardedAdId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [testDevices](test-devices.html) | `abstract val testDevices: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

### Companion Object Properties

| [DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS](-d-e-f-a-u-l-t_-d-e-l-a-y_-b-e-t-w-e-e-n_-i-n-t-e-r-s-t-i-t-i-a-l_-a-d-s.html) | `const val DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [REWARD_AD_MAX_RETRY_COUNT](-r-e-w-a-r-d_-a-d_-m-a-x_-r-e-t-r-y_-c-o-u-n-t.html) | `const val REWARD_AD_MAX_RETRY_COUNT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Inheritors

| [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.html) | `interface IAppConfig : `[`IAdsConfig`](./index.html)`, `[`IGDPRConsentConfig`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.html)`, `[`ISplashConfig`](../../pl.netigen.coreapi.splash/-i-splash-config/index.html) |

