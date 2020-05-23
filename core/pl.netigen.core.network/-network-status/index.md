---
title: NetworkStatus - core
---

[api_android](../index.md)/[core](../../index.md)/[pl.netigen.core.network](../index.md)/[NetworkStatus](./index.md)

# NetworkStatus

`class NetworkStatus : INetworkStatus`

### Constructors

| [&lt;init&gt;](-init-.html) | `NetworkStatus(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

### Properties

| [isConnectedOrConnecting](is-connected-or-connecting.html) | `val isConnectedOrConnecting: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [addNetworkStatusChangeListener](add-network-status-change-listener.html) | `fun addNetworkStatusChangeListener(networkStatusChangeListener: NetworkStatusChangeListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [removeNetworkStatusChangeListener](remove-network-status-change-listener.html) | `fun removeNetworkStatusChangeListener(networkStatusChangeListener: NetworkStatusChangeListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [requestNetworkState](request-network-state.html) | `suspend fun requestNetworkState(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Companion Object Properties

| [CONNECTION_CHECK_TIMEOUT_MS](-c-o-n-n-e-c-t-i-o-n_-c-h-e-c-k_-t-i-m-e-o-u-t_-m-s.html) | `const val CONNECTION_CHECK_TIMEOUT_MS: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |
| [GOOGLE_DNS_HOSTNAME](-g-o-o-g-l-e_-d-n-s_-h-o-s-t-n-a-m-e.html) | `const val GOOGLE_DNS_HOSTNAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [GOOGLE_DNS_PORT](-g-o-o-g-l-e_-d-n-s_-p-o-r-t.html) | `const val GOOGLE_DNS_PORT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |

