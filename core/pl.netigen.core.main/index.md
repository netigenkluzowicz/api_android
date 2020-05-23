---
title: pl.netigen.core.main - core
---

[core](../index.html) / [pl.netigen.core.main](./index.html)

## Package pl.netigen.core.main

### Types

| [CoreMainActivity](-core-main-activity/index.html) | Implements [ICoreMainActivity](#)`abstract class CoreMainActivity : `[`AppCompatActivity`](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)`, ICoreMainActivity` |
| [CoreMainVmImpl](-core-main-vm-impl/index.html) | Current [ICoreMainVM](#) implementation, provided implementations should be singletons`class CoreMainVmImpl : CoreMainVM, IPayments, IAds, INetworkStatus, IGDPRConsent, IAppConfig` |
| [CoreViewModelsFactory](-core-view-models-factory/index.html) | [ICoreViewModelsFactory](#) using companion object for providing Api modules singletons for created view models`abstract class CoreViewModelsFactory : ICoreViewModelsFactory` |

