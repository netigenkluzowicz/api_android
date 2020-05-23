---
title: CoreViewModelsFactory - core
---

[api_android](../index.md)(../index.md)/[core](../../index.md) / [pl.netigen.core.main](../index.md) / [CoreViewModelsFactory](./index.md)

# CoreViewModelsFactory

`abstract class CoreViewModelsFactory : ICoreViewModelsFactory`

[ICoreViewModelsFactory](#) using companion object for providing Api modules singletons for created view models

### Constructors

| [&lt;init&gt;](-init-.html) | [ICoreViewModelsFactory](#) using companion object for providing Api modules singletons for created view models`CoreViewModelsFactory(coreMainActivity: `[`CoreMainActivity`](../-core-main-activity/index.md)`)` |

### Properties

| [coreMainActivity](core-main-activity.html) | `open val coreMainActivity: `[`CoreMainActivity`](../-core-main-activity/index.md) |
| [networkStatus](network-status.html) | `open val networkStatus: INetworkStatus` |

### Functions

| [create](create.html) | Creates a new instance of the given [SplashVM](#) or [CoreMainVM](#)`open fun <T : `[`ViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)`?> create(modelClass: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): T` |

### Companion Object Functions

| [cleanAds](clean-ads.html) | Used to clean up ads instance after [CoreMainVM](#) is cleared(activity is killed)`fun cleanAds(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

