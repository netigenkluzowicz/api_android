---
title: IGDPRConsent - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.gdpr](../index.html) / [IGDPRConsent](./index.html)

# IGDPRConsent

`interface IGDPRConsent : `[`IGDPRTexts`](../-i-g-d-p-r-texts/index.html)

Its used for collect user consent for displaying ads, and ask them if we can provide personalized ads

Ads policies to your users in the European Economic Area (EEA)
and the UK and obtain their consent for the use of cookies or other local storage where legally required,
and for the collection, sharing, and use of personal data for ads personalization.
This policies reflects the requirements of the EU ePrivacy Directive and the General Data Protection Regulation (GDPR).

[More info Admob](https://github.com/googleads/googleads-consent-sdk-android)

[More info HMS Ads Kit](https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/ads-sdk-settings#h1-1585566951865)

### Properties

| [adConsentStatus](ad-consent-status.html) | Provides current Ad consent status`abstract val adConsentStatus: Flow<`[`AdConsentStatus`](../-ad-consent-status/index.html)`>` |

### Functions

| [requestGDPRLocation](request-g-d-p-r-location.html) | Request check if user is located in place where we should display consent to him`abstract fun requestGDPRLocation(): Flow<`[`CheckGDPRLocationStatus`](../-check-g-d-p-r-location-status/index.html)`>` |
| [saveAdConsentStatus](save-ad-consent-status.html) | Saves current consent status:`abstract fun saveAdConsentStatus(adConsentStatus: `[`AdConsentStatus`](../-ad-consent-status/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [CoreMainVmImpl](../../pl.netigen.core.main/-core-main-vm-impl/index.html) | Current [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) implementation, provided implementations should be singletons`class CoreMainVmImpl : `[`CoreMainVM`](../../pl.netigen.coreapi.main/-core-main-v-m/index.html)`, `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](./index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |
| [GDPRConsentImpl](../../pl.netigen.gms.gdpr/-g-d-p-r-consent-impl/index.html) | [IGDPRConsent](./index.html) implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)`class GDPRConsentImpl : `[`IGDPRConsent`](./index.html)`, `[`IGDPRTexts`](../-i-g-d-p-r-texts/index.html) |
| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](./index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |

