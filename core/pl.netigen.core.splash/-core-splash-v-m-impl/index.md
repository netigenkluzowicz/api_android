---
title: CoreSplashVMImpl - core
---

[core](../../index.html) / [pl.netigen.core.splash](../index.html) / [CoreSplashVMImpl](./index.html)

# CoreSplashVMImpl

`class CoreSplashVMImpl : SplashVM, INoAds, IAppConfig`

[SplashVM](#) implementation

### Constructors

| [&lt;init&gt;](-init-.html) | [SplashVM](#) implementation`CoreSplashVMImpl(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`, gdprConsent: IGDPRConsent, ads: IAds, noAdsPurchases: INoAds, networkStatus: INetworkStatus, appConfig: IAppConfig, splashTimer: ISplashTimer = SplashTimerImpl(appConfig.maxConsentWaitTime, appConfig.maxInterstitialWaitTime), coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO)` |

### Properties

| [coroutineDispatcherIo](coroutine-dispatcher-io.html) | `val coroutineDispatcherIo: CoroutineDispatcher` |
| [gdprConsent](gdpr-consent.html) | [IGDPRConsent](#) instance`val gdprConsent: IGDPRConsent` |
| [isFirstLaunch](is-first-launch.html) | `val isFirstLaunch: `[`MutableLiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [splashState](splash-state.html) | `val splashState: `[`MutableLiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)`<SplashState>` |

### Functions

| [onCleared](on-cleared.html) | `fun onCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setPersonalizedAds](set-personalized-ads.html) | `fun setPersonalizedAds(personalizedAdsApproved: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.html) | `fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

