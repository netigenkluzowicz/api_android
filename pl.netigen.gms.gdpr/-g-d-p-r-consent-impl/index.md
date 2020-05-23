---
title: GDPRConsentImpl - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.gdpr](../index.html) / [GDPRConsentImpl](./index.html)

# GDPRConsentImpl

`class GDPRConsentImpl : `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IGDPRTexts`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-texts/index.html)

[IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html) implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)

### Constructors

| [&lt;init&gt;](-init-.html) | [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html) implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)`GDPRConsentImpl(application: Application, config: `[`IGDPRConsentConfig`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.html)`)` |

### Properties

| [adConsentStatus](ad-consent-status.html) | Provides current Ad consent status`val adConsentStatus: Flow<`[`AdConsentStatus`](../../pl.netigen.coreapi.gdpr/-ad-consent-status/index.html)`>` |
| [consentInformation](consent-information.html) | `val consentInformation: ConsentInformation` |

### Functions

| [requestGDPRLocation](request-g-d-p-r-location.html) | Request check if user is located in place where we should display consent to him`fun requestGDPRLocation(): Flow<`[`CheckGDPRLocationStatus`](../../pl.netigen.coreapi.gdpr/-check-g-d-p-r-location-status/index.html)`>` |
| [saveAdConsentStatus](save-ad-consent-status.html) | Saves current consent status:`fun saveAdConsentStatus(adConsentStatus: `[`AdConsentStatus`](../../pl.netigen.coreapi.gdpr/-ad-consent-status/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| [PREFERENCES_KEY](-p-r-e-f-e-r-e-n-c-e-s_-k-e-y.html) | `const val PREFERENCES_KEY: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [PREFERENCES_NAME](-p-r-e-f-e-r-e-n-c-e-s_-n-a-m-e.html) | `const val PREFERENCES_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

