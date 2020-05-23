---
title: SplashTimerImpl - api_android
---

[api_android](../../index.html) / [pl.netigen.core.splash](../index.html) / [SplashTimerImpl](./index.html)

# SplashTimerImpl

`class SplashTimerImpl : `[`ISplashTimer`](../../pl.netigen.coreapi.splash/-i-splash-timer/index.html)

[ISplashTimer](../../pl.netigen.coreapi.splash/-i-splash-timer/index.html) implementation

### Constructors

| [&lt;init&gt;](-init-.html) | [ISplashTimer](../../pl.netigen.coreapi.splash/-i-splash-timer/index.html) implementation`SplashTimerImpl(consentTimeLimit: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_MAX_CONSENT_WAIT_TIME_MS, maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS)` |

### Functions

| [cancelConsentTimer](cancel-consent-timer.html) | `fun cancelConsentTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelInterstitialTimer](cancel-interstitial-timer.html) | `fun cancelInterstitialTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelTimers](cancel-timers.html) | `fun cancelTimers(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startConsentTimer](start-consent-timer.html) | Starts consent timer`fun startConsentTimer(onConsentTimeLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startInterstitialTimer](start-interstitial-timer.html) | Starts splash interstitial ad timer`fun startInterstitialTimer(onLoadSplashLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

