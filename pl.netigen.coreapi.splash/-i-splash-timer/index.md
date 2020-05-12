---
title: ISplashTimer - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.splash](../index.html) / [ISplashTimer](./index.html)

# ISplashTimer

`interface ISplashTimer`

Timer utils for splash

### Functions

| [cancelConsentTimer](cancel-consent-timer.html) | `abstract fun cancelConsentTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelInterstitialTimer](cancel-interstitial-timer.html) | `abstract fun cancelInterstitialTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelTimers](cancel-timers.html) | `abstract fun cancelTimers(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startConsentTimer](start-consent-timer.html) | `abstract fun startConsentTimer(onConsentTimeLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startInterstitialTimer](start-interstitial-timer.html) | `abstract fun startInterstitialTimer(onLoadSplashLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

