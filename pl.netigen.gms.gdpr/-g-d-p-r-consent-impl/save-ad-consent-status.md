---
title: GDPRConsentImpl.saveAdConsentStatus - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.gdpr](../index.html) / [GDPRConsentImpl](index.html) / [saveAdConsentStatus](./save-ad-consent-status.html)

# saveAdConsentStatus

`fun saveAdConsentStatus(adConsentStatus: `[`AdConsentStatus`](../../pl.netigen.coreapi.gdpr/-ad-consent-status/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Saves current consent status:

* [AdConsentStatus.PERSONALIZED_NON_UE](../../pl.netigen.coreapi.gdpr/-ad-consent-status/-p-e-r-s-o-n-a-l-i-z-e-d_-n-o-n_-u-e.html) - user location allows use of personalized ads
* [AdConsentStatus.PERSONALIZED_SHOWED](../../pl.netigen.coreapi.gdpr/-ad-consent-status/-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.html) - consent was collected from user with personalized ads allowed
* [AdConsentStatus.NON_PERSONALIZED_SHOWED](../../pl.netigen.coreapi.gdpr/-ad-consent-status/-n-o-n_-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.html) - consent was collected from user with personalized ads not allowed
* [AdConsentStatus.UNINITIALIZED](../../pl.netigen.coreapi.gdpr/-ad-consent-status/-u-n-i-n-i-t-i-a-l-i-z-e-d.html) - consent was not collected and user location is not checked- ads should not be displayed

### Parameters

`adConsentStatus` - Collected consent information to save