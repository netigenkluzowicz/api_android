---
title: GDPRConsentImpl - gms
---

[gms](../../index.md) / [pl.netigen.gms.gdpr](../index.md) / [GDPRConsentImpl](./index.md)

# GDPRConsentImpl

`class GDPRConsentImpl : IGDPRConsent, IGDPRTexts`

[IGDPRConsent](#) implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)

### Constructors

| [&lt;init&gt;](-init-.md)) | [IGDPRConsent](#) implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)`GDPRConsentImpl(application: `[`Application`](https://developer.android.com/reference/android/app/Application.md))`, config: IGDPRConsentConfig)` |

### Properties

| [adConsentStatus](ad-consent-status.md)) | `val adConsentStatus: Flow<AdConsentStatus>` |
| [consentInformation](consent-information.md)) | `val consentInformation: ConsentInformation` |

### Functions

| [requestGDPRLocation](request-g-d-p-r-location.md)) | `fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus>` |
| [saveAdConsentStatus](save-ad-consent-status.md)) | `fun saveAdConsentStatus(adConsentStatus: AdConsentStatus): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Companion Object Properties

| [PREFERENCES_KEY](-p-r-e-f-e-r-e-n-c-e-s_-k-e-y.md)) | `const val PREFERENCES_KEY: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [PREFERENCES_NAME](-p-r-e-f-e-r-e-n-c-e-s_-n-a-m-e.md)) | `const val PREFERENCES_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |

