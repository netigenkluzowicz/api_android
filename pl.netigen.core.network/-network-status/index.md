---
title: NetworkStatus - api_android
---

[api_android](../../index.html) / [pl.netigen.core.network](../index.html) / [NetworkStatus](./index.html)

# NetworkStatus

`class NetworkStatus : `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)

### Constructors

| [&lt;init&gt;](-init-.html) | `NetworkStatus(application: Application)` |

### Properties

| [isConnectedOrConnecting](is-connected-or-connecting.html) | `val isConnectedOrConnecting: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [addNetworkStatusChangeListener](add-network-status-change-listener.html) | `fun addNetworkStatusChangeListener(networkStatusChangeListener: `[`NetworkStatusChangeListener`](../../pl.netigen.coreapi.network/-network-status-change-listener/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeNetworkStatusChangeListener](remove-network-status-change-listener.html) | `fun removeNetworkStatusChangeListener(networkStatusChangeListener: `[`NetworkStatusChangeListener`](../../pl.netigen.coreapi.network/-network-status-change-listener/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [requestNetworkState](request-network-state.html) | `suspend fun requestNetworkState(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Companion Object Properties

| [CONNECTION_CHECK_TIMEOUT_MS](-c-o-n-n-e-c-t-i-o-n_-c-h-e-c-k_-t-i-m-e-o-u-t_-m-s.html) | `const val CONNECTION_CHECK_TIMEOUT_MS: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [GOOGLE_DNS_HOSTNAME](-g-o-o-g-l-e_-d-n-s_-h-o-s-t-n-a-m-e.html) | `const val GOOGLE_DNS_HOSTNAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [GOOGLE_DNS_PORT](-g-o-o-g-l-e_-d-n-s_-p-o-r-t.html) | `const val GOOGLE_DNS_PORT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

