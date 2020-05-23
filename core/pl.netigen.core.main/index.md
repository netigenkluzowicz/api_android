---
title: pl.netigen.core.main - core
---

[core](../index.md) / [pl.netigen.core.main](./index.md)

## Package pl.netigen.core.main

### Types

| [CoreMainActivity](-core-main-activity/index.md) | Implements [ICoreMainActivity](#)`abstract class CoreMainActivity : `[`AppCompatActivity`](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatActivity.md))`, ICoreMainActivity` |
| [CoreMainVmImpl](-core-main-vm-impl/index.md) | Current [ICoreMainVM](#) implementation, provided implementations should be singletons`class CoreMainVmImpl : CoreMainVM, IPayments, IAds, INetworkStatus, IGDPRConsent, IAppConfig` |
| [CoreViewModelsFactory](-core-view-models-factory/index.md) | [ICoreViewModelsFactory](#) using companion object for providing Api modules singletons for created view models`abstract class CoreViewModelsFactory : ICoreViewModelsFactory` |

