---
title: INetworkStatus - core-api
---

[home page](../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.network](../index.md) / [INetworkStatus](./index.md)

# INetworkStatus

`interface INetworkStatus`

### Properties

| [isConnectedOrConnecting](is-connected-or-connecting.html) | `abstract val isConnectedOrConnecting: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [addNetworkStatusChangeListener](add-network-status-change-listener.html) | `abstract fun addNetworkStatusChangeListener(networkStatusChangeListener: `[`NetworkStatusChangeListener`](../-network-status-change-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [removeNetworkStatusChangeListener](remove-network-status-change-listener.html) | `abstract fun removeNetworkStatusChangeListener(networkStatusChangeListener: `[`NetworkStatusChangeListener`](../-network-status-change-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [requestNetworkState](request-network-state.html) | `abstract suspend fun requestNetworkState(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.md) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](./index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md) |

