---
title: IGDPRConsent - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.gdpr](../index.html) / [IGDPRConsent](./index.html)

# IGDPRConsent

`interface IGDPRConsent : `[`IGDPRTexts`](../-i-g-d-p-r-texts/index.html)

### Properties

| [adConsentStatus](ad-consent-status.html) | `abstract val adConsentStatus: Flow<`[`AdConsentStatus`](../-ad-consent-status/index.html)`>` |

### Functions

| [requestGDPRLocation](request-g-d-p-r-location.html) | `abstract fun requestGDPRLocation(): Flow<`[`CheckGDPRLocationStatus`](../-check-g-d-p-r-location-status/index.html)`>` |
| [saveAdConsentStatus](save-ad-consent-status.html) | `abstract fun saveAdConsentStatus(adConsentStatus: `[`AdConsentStatus`](../-ad-consent-status/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) | `interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](./index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |

