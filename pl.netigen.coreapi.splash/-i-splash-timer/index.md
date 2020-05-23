---
title: ISplashTimer - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.splash](../index.html) / [ISplashTimer](./index.html)

# ISplashTimer

`interface ISplashTimer`

Timer utils for [CoreSplashVMImpl](../../pl.netigen.core.splash/-core-splash-v-m-impl/index.html)

### Functions

| [cancelConsentTimer](cancel-consent-timer.html) | `abstract fun cancelConsentTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelInterstitialTimer](cancel-interstitial-timer.html) | `abstract fun cancelInterstitialTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancelTimers](cancel-timers.html) | `abstract fun cancelTimers(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startConsentTimer](start-consent-timer.html) | Starts consent timer`abstract fun startConsentTimer(onConsentTimeLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startInterstitialTimer](start-interstitial-timer.html) | Starts splash interstitial ad timer`abstract fun startInterstitialTimer(onLoadSplashLimit: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [SplashTimerImpl](../../pl.netigen.core.splash/-splash-timer-impl/index.html) | [ISplashTimer](./index.html) implementation`class SplashTimerImpl : `[`ISplashTimer`](./index.html) |

