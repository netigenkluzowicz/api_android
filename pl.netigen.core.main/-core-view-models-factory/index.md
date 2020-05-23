---
title: CoreViewModelsFactory - api_android
---

[api_android](../../index.html) / [pl.netigen.core.main](../index.html) / [CoreViewModelsFactory](./index.html)

# CoreViewModelsFactory

`abstract class CoreViewModelsFactory : `[`ICoreViewModelsFactory`](../../pl.netigen.coreapi.main/-i-core-view-models-factory/index.html)

[ICoreViewModelsFactory](../../pl.netigen.coreapi.main/-i-core-view-models-factory/index.html) using companion object for providing Api modules singletons for created view models

### Constructors

| [&lt;init&gt;](-init-.html) | [ICoreViewModelsFactory](../../pl.netigen.coreapi.main/-i-core-view-models-factory/index.html) using companion object for providing Api modules singletons for created view models`CoreViewModelsFactory(coreMainActivity: `[`CoreMainActivity`](../-core-main-activity/index.html)`)` |

### Properties

| [coreMainActivity](core-main-activity.html) | Main api activity provides context for Api modules`open val coreMainActivity: `[`CoreMainActivity`](../-core-main-activity/index.html) |
| [networkStatus](network-status.html) | Provides [INetworkStatus](../../pl.netigen.coreapi.network/-i-network-status/index.html) to Api view models`open val networkStatus: `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html) |

### Functions

| [create](create.html) | Creates a new instance of the given [SplashVM](../../pl.netigen.coreapi.splash/-splash-v-m/index.html) or [CoreMainVM](../../pl.netigen.coreapi.main/-core-main-v-m/index.html)`open fun <T : `[`ViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)`?> create(modelClass: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): T` |

### Companion Object Functions

| [cleanAds](clean-ads.html) | Used to clean up ads instance after [CoreMainVM](../../pl.netigen.coreapi.main/-core-main-v-m/index.html) is cleared(activity is killed)`fun cleanAds(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

