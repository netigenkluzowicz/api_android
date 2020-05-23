---
title: ISplashTimer - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.splash](../index.md) / [ISplashTimer](./index.md)

# ISplashTimer

`interface ISplashTimer`

Timer utils for [CoreSplashVMImpl](#)

### Functions

| [cancelConsentTimer](cancel-consent-timer.md)) | `abstract fun cancelConsentTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [cancelInterstitialTimer](cancel-interstitial-timer.md)) | `abstract fun cancelInterstitialTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [cancelTimers](cancel-timers.md)) | `abstract fun cancelTimers(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [startConsentTimer](start-consent-timer.md)) | Starts consent timer`abstract fun startConsentTimer(onConsentTimeLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [startInterstitialTimer](start-interstitial-timer.md)) | Starts splash interstitial ad timer`abstract fun startInterstitialTimer(onLoadSplashLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

