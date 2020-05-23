---
title: GDPRConsentImpl - gms
---

[gms](../../index.md) / [pl.netigen.gms.gdpr](../index.md) / [GDPRConsentImpl](./index.md)

# GDPRConsentImpl

`class GDPRConsentImpl : IGDPRConsent, IGDPRTexts`

[IGDPRConsent](#) implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)

### Constructors

| [&lt;init&gt;](-init-.html) | [IGDPRConsent](#) implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)`GDPRConsentImpl(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`, config: IGDPRConsentConfig)` |

### Properties

| [adConsentStatus](ad-consent-status.html) | `val adConsentStatus: Flow<AdConsentStatus>` |
| [consentInformation](consent-information.html) | `val consentInformation: ConsentInformation` |

### Functions

| [requestGDPRLocation](request-g-d-p-r-location.html) | `fun requestGDPRLocation(): Flow<CheckGDPRLocationStatus>` |
| [saveAdConsentStatus](save-ad-consent-status.html) | `fun saveAdConsentStatus(adConsentStatus: AdConsentStatus): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Companion Object Properties

| [PREFERENCES_KEY](-p-r-e-f-e-r-e-n-c-e-s_-k-e-y.html) | `const val PREFERENCES_KEY: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [PREFERENCES_NAME](-p-r-e-f-e-r-e-n-c-e-s_-n-a-m-e.html) | `const val PREFERENCES_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |

