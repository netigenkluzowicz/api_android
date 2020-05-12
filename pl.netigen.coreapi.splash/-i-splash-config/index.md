---
title: ISplashConfig - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.splash](../index.html) / [ISplashConfig](./index.html)

# ISplashConfig

`interface ISplashConfig`

Base Splash configuration

### Properties

| [isNoAdsAvailable](is-no-ads-available.html) | Set if there is or isn't no-ads payment in application`abstract val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [maxConsentWaitTime](max-consent-wait-time.html) | Max white time on first splash launch to fetch consent information from web services`abstract val maxConsentWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [maxInterstitialWaitTime](max-interstitial-wait-time.html) | Max white time for load splash interstitial ad, it is start counting after consent operations are finished`abstract val maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |

### Companion Object Properties

| [DEFAULT_MAX_CONSENT_WAIT_TIME_MS](-d-e-f-a-u-l-t_-m-a-x_-c-o-n-s-e-n-t_-w-a-i-t_-t-i-m-e_-m-s.html) | `const val DEFAULT_MAX_CONSENT_WAIT_TIME_MS: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS](-d-e-f-a-u-l-t_-s-p-l-a-s-h_-m-a-x_-l-o-a-d_-i-n-t-e-r-s-t-i-t-i-a-l_-w-a-i-t_-t-i-m-e_-m-s.html) | `const val DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |

### Inheritors

| [IAppConfig](../../pl.netigen.coreapi.main/-i-app-config/index.html) | `interface IAppConfig : `[`IAdsConfig`](../../pl.netigen.coreapi.ads/-i-ads-config/index.html)`, `[`IGDPRConsentConfig`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.html)`, `[`ISplashConfig`](./index.html) |

