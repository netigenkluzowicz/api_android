---
title: CoreSplashVMImpl - api_android
---

[api_android](../../index.html) / [pl.netigen.core.splash](../index.html) / [CoreSplashVMImpl](./index.html)

# CoreSplashVMImpl

`class CoreSplashVMImpl : `[`SplashVM`](../../pl.netigen.coreapi.splash/-splash-v-m/index.html)`, `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html)

[SplashVM](../../pl.netigen.coreapi.splash/-splash-v-m/index.html) implementation

### Constructors

| [&lt;init&gt;](-init-.html) | [SplashVM](../../pl.netigen.coreapi.splash/-splash-v-m/index.html) implementation`CoreSplashVMImpl(application: Application, gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, ads: `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, noAdsPurchases: `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.html)`, networkStatus: `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, appConfig: `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html)`, splashTimer: `[`ISplashTimer`](../../pl.netigen.coreapi.splash/-i-splash-timer/index.html)` = SplashTimerImpl(appConfig.maxConsentWaitTime, appConfig.maxInterstitialWaitTime), coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO)` |

### Properties

| [coroutineDispatcherIo](coroutine-dispatcher-io.html) | `val coroutineDispatcherIo: CoroutineDispatcher` |
| [gdprConsent](gdpr-consent.html) | [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html) instance`val gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html) |
| [isFirstLaunch](is-first-launch.html) | `val isFirstLaunch: `[`MutableLiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [splashState](splash-state.html) | `val splashState: `[`MutableLiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)`<`[`SplashState`](../../pl.netigen.coreapi.splash/-splash-state/index.html)`>` |

### Functions

| [onCleared](on-cleared.html) | `fun onCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setPersonalizedAds](set-personalized-ads.html) | `fun setPersonalizedAds(personalizedAdsApproved: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.html) | `fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

