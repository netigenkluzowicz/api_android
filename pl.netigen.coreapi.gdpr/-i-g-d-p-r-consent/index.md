[core-api](../../index.md) / [pl.netigen.coreapi.gdpr](../index.md) / [IGDPRConsent](./index.md)

# IGDPRConsent

`interface IGDPRConsent : `[`IGDPRTexts`](../-i-g-d-p-r-texts/index.md)

### Properties

| [adConsentStatus](ad-consent-status.md) | `abstract val adConsentStatus: Flow<`[`AdConsentStatus`](../-ad-consent-status/index.md)`>` |

### Functions

| [requestGDPRLocation](request-g-d-p-r-location.md) | `abstract fun requestGDPRLocation(): Flow<`[`CheckGDPRLocationStatus`](../-check-g-d-p-r-location-status/index.md)`>` |
| [saveAdConsentStatus](save-ad-consent-status.md) | `abstract fun saveAdConsentStatus(adConsentStatus: `[`AdConsentStatus`](../-ad-consent-status/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.md) | `interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](./index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md) |

