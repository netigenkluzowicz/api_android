---
title: IGDPRConsent.saveAdConsentStatus - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.gdpr](../index.md) / [IGDPRConsent](index.md) / [saveAdConsentStatus](./save-ad-consent-status.html)

# saveAdConsentStatus

`abstract fun saveAdConsentStatus(adConsentStatus: `[`AdConsentStatus`](../-ad-consent-status/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)

Saves current consent status:

* [AdConsentStatus.PERSONALIZED_NON_UE](../-ad-consent-status/-p-e-r-s-o-n-a-l-i-z-e-d_-n-o-n_-u-e.html) - user location allows use of personalized ads
* [AdConsentStatus.PERSONALIZED_SHOWED](../-ad-consent-status/-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.html) - consent was collected from user with personalized ads allowed
* [AdConsentStatus.NON_PERSONALIZED_SHOWED](../-ad-consent-status/-n-o-n_-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.html) - consent was collected from user with personalized ads not allowed
* [AdConsentStatus.UNINITIALIZED](../-ad-consent-status/-u-n-i-n-i-t-i-a-l-i-z-e-d.html) - consent was not collected and user location is not checked- ads should not be displayed

### Parameters

`adConsentStatus` - Collected consent information to save