---
title: CoreViewModelsFactory - core
---

[core](../../index.md) / [pl.netigen.core.main](../index.md) / [CoreViewModelsFactory](./index.md)

# CoreViewModelsFactory

`abstract class CoreViewModelsFactory : ICoreViewModelsFactory`

[ICoreViewModelsFactory](#) using companion object for providing Api modules singletons for created view models

### Constructors

| [&lt;init&gt;](-init-.md)) | [ICoreViewModelsFactory](#) using companion object for providing Api modules singletons for created view models`CoreViewModelsFactory(coreMainActivity: `[`CoreMainActivity`](../-core-main-activity/index.md)`)` |

### Properties

| [coreMainActivity](core-main-activity.md)) | `open val coreMainActivity: `[`CoreMainActivity`](../-core-main-activity/index.md) |
| [networkStatus](network-status.md)) | `open val networkStatus: INetworkStatus` |

### Functions

| [create](create.md)) | Creates a new instance of the given [SplashVM](#) or [CoreMainVM](#)`open fun <T : `[`ViewModel`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/ViewModel.md))`?> create(modelClass: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.md))`<T>): T` |

### Companion Object Functions

| [cleanAds](clean-ads.md)) | Used to clean up ads instance after [CoreMainVM](#) is cleared(activity is killed)`fun cleanAds(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

