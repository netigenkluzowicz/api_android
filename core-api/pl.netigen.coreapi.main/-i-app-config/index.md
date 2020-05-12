[core-api](../../index.md) / [pl.netigen.coreapi.main](../index.md) / [IAppConfig](./index.md)

# IAppConfig

`interface IAppConfig : `[`IAdsConfig`](../../pl.netigen.coreapi.ads/-i-ads-config/index.md)`, `[`IGDPRConsentConfig`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.md)`, `[`ISplashConfig`](../../pl.netigen.coreapi.splash/-i-splash-config/index.md)

### Properties

| Name | Summary |
|---|---|
| [inDebugMode](in-debug-mode.md) | `abstract val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNoAdsAvailable](is-no-ads-available.md) | Set if there is or isn't no-ads payment in application`abstract val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [store](store.md) | `abstract val store: `[`Store`](../-store/index.md) |

### Inheritors

| Name | Summary |
|---|---|
| [ICoreMainVM](../-i-core-main-v-m/index.md) | `interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](./index.md) |
| [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.md) | `interface ISplashVM : `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.md)`, `[`IAppConfig`](./index.md) |
