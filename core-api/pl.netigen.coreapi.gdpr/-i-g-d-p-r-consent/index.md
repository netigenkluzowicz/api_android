---
title: IGDPRConsent - core-api
---

[api_android](../index.md)/[core-api](../../index.md)/[pl.netigen.coreapi.gdpr](../index.md)/[IGDPRConsent](./index.md)

# IGDPRConsent

`interface IGDPRConsent : `[`IGDPRTexts`](../-i-g-d-p-r-texts/index.md)

Its used for collect user consent for displaying ads, and ask them if we can provide personalized ads

Ads policies to your users in the European Economic Area (EEA)
and the UK and obtain their consent for the use of cookies or other local storage where legally required,
and for the collection, sharing, and use of personal data for ads personalization.
This policies reflects the requirements of the EU ePrivacy Directive and the General Data Protection Regulation (GDPR).

[More info Admob](https://github.com/googleads/googleads-consent-sdk-android)

[More info HMS Ads Kit](https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/ads-sdk-settings#h1-1585566951865)

### Properties

| [adConsentStatus](ad-consent-status.html) | Provides current Ad consent status`abstract val adConsentStatus: Flow<`[`AdConsentStatus`](../-ad-consent-status/index.md)`>` |

### Functions

| [requestGDPRLocation](request-g-d-p-r-location.html) | Request check if user is located in place where we should display consent to him`abstract fun requestGDPRLocation(): Flow<`[`CheckGDPRLocationStatus`](../-check-g-d-p-r-location-status/index.md)`>` |
| [saveAdConsentStatus](save-ad-consent-status.html) | Saves current consent status:`abstract fun saveAdConsentStatus(adConsentStatus: `[`AdConsentStatus`](../-ad-consent-status/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.md) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](./index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md) |

