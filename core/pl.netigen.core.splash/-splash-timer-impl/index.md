---
title: SplashTimerImpl - core
---

[core](../../index.md) / [pl.netigen.core.splash](../index.md) / [SplashTimerImpl](./index.md)

# SplashTimerImpl

`class SplashTimerImpl : ISplashTimer`

[ISplashTimer](#) implementation

### Constructors

| [&lt;init&gt;](-init-.md)) | [ISplashTimer](#) implementation`SplashTimerImpl(consentTimeLimit: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md)` = DEFAULT_MAX_CONSENT_WAIT_TIME_MS, maxInterstitialWaitTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md)` = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS)` |

### Functions

| [cancelConsentTimer](cancel-consent-timer.md)) | `fun cancelConsentTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [cancelInterstitialTimer](cancel-interstitial-timer.md)) | `fun cancelInterstitialTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [cancelTimers](cancel-timers.md)) | `fun cancelTimers(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [startConsentTimer](start-consent-timer.md)) | `fun startConsentTimer(onConsentTimeLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [startInterstitialTimer](start-interstitial-timer.md)) | `fun startInterstitialTimer(onLoadSplashLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

