---
title: CoreSplashVMImpl - core
---

[core](../../index.md) / [pl.netigen.core.splash](../index.md) / [CoreSplashVMImpl](./index.md)

# CoreSplashVMImpl

`class CoreSplashVMImpl : SplashVM, INoAds, IAppConfig`

[SplashVM](#) implementation

### Constructors

| [&lt;init&gt;](-init-.md)) | [SplashVM](#) implementation`CoreSplashVMImpl(application: `[`Application`](https://developer.android.com/reference/android/app/Application.md))`, gdprConsent: IGDPRConsent, ads: IAds, noAdsPurchases: INoAds, networkStatus: INetworkStatus, appConfig: IAppConfig, splashTimer: ISplashTimer = SplashTimerImpl(appConfig.maxConsentWaitTime, appConfig.maxInterstitialWaitTime), coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO)` |

### Properties

| [coroutineDispatcherIo](coroutine-dispatcher-io.md)) | `val coroutineDispatcherIo: CoroutineDispatcher` |
| [gdprConsent](gdpr-consent.md)) | [IGDPRConsent](#) instance`val gdprConsent: IGDPRConsent` |
| [isFirstLaunch](is-first-launch.md)) | `val isFirstLaunch: `[`MutableLiveData`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/MutableLiveData.md))`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [splashState](splash-state.md)) | `val splashState: `[`MutableLiveData`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/MutableLiveData.md))`<SplashState>` |

### Functions

| [onCleared](on-cleared.md)) | `fun onCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setPersonalizedAds](set-personalized-ads.md)) | `fun setPersonalizedAds(personalizedAdsApproved: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [start](start.md)) | `fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

