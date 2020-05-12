[core-api](../../index.md) / [pl.netigen.coreapi.network](../index.md) / [INetworkStatus](./index.md)

# INetworkStatus

`interface INetworkStatus`

### Properties

| [isConnectedOrConnecting](is-connected-or-connecting.md) | `abstract val isConnectedOrConnecting: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [addNetworkStatusChangeListener](add-network-status-change-listener.md) | `abstract fun addNetworkStatusChangeListener(networkStatusChangeListener: `[`NetworkStatusChangeListener`](../-network-status-change-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeNetworkStatusChangeListener](remove-network-status-change-listener.md) | `abstract fun removeNetworkStatusChangeListener(networkStatusChangeListener: `[`NetworkStatusChangeListener`](../-network-status-change-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [requestNetworkState](request-network-state.md) | `abstract suspend fun requestNetworkState(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.md) | `interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](./index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md) |

