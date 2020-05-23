---
title: CoreMainVM - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.main](../index.html) / [CoreMainVM](./index.html)

# CoreMainVM

`abstract class CoreMainVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ICoreMainVM`](../-i-core-main-v-m/index.html)

Base class for [ICoreMainVM](../-i-core-main-v-m/index.html) implementation, extends [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) because it must be available from any Fragment or Activity

For easy access in fragments use [NetigenVMFragment](../../pl.netigen.core.fragment/-netigen-v-m-fragment/index.html)

see: [ICoreMainVM](../-i-core-main-v-m/index.html), [ICoreMainActivity](../-i-core-main-activity/index.html)

### Parameters

`application` - [Application](#)  context of this class

### Constructors

| [&lt;init&gt;](-init-.html) | Base class for [ICoreMainVM](../-i-core-main-v-m/index.html) implementation, extends [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) because it must be available from any Fragment or Activity`CoreMainVM(application: Application)` |

### Inheritors

| [CoreMainVmImpl](../../pl.netigen.core.main/-core-main-vm-impl/index.html) | Current [ICoreMainVM](../-i-core-main-v-m/index.html) implementation, provided implementations should be singletons`class CoreMainVmImpl : `[`CoreMainVM`](./index.html)`, `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../-i-app-config/index.html) |

