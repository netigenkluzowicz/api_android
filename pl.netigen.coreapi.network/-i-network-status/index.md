---
title: INetworkStatus - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.network](../index.html) / [INetworkStatus](./index.html)

# INetworkStatus

`interface INetworkStatus`

### Properties

| [isConnectedOrConnecting](is-connected-or-connecting.html) | `abstract val isConnectedOrConnecting: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [addNetworkStatusChangeListener](add-network-status-change-listener.html) | `abstract fun addNetworkStatusChangeListener(networkStatusChangeListener: `[`NetworkStatusChangeListener`](../-network-status-change-listener/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeNetworkStatusChangeListener](remove-network-status-change-listener.html) | `abstract fun removeNetworkStatusChangeListener(networkStatusChangeListener: `[`NetworkStatusChangeListener`](../-network-status-change-listener/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [requestNetworkState](request-network-state.html) | `abstract suspend fun requestNetworkState(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| [CoreMainVmImpl](../../pl.netigen.core.main/-core-main-vm-impl/index.html) | Current [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) implementation, provided implementations should be singletons`class CoreMainVmImpl : `[`CoreMainVM`](../../pl.netigen.coreapi.main/-core-main-v-m/index.html)`, `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](./index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |
| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](./index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |
| [NetworkStatus](../../pl.netigen.core.network/-network-status/index.html) | `class NetworkStatus : `[`INetworkStatus`](./index.html) |

