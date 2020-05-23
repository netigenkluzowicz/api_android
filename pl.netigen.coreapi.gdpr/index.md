---
title: pl.netigen.coreapi.gdpr - api_android
---

[api_android](../index.html) / [pl.netigen.coreapi.gdpr](./index.html)

## Package pl.netigen.coreapi.gdpr

### Types

| [AdConsentStatus](-ad-consent-status/index.html) | Possible values of Gdpr ads consent status`enum class AdConsentStatus` |
| [CheckGDPRLocationStatus](-check-g-d-p-r-location-status/index.html) | Possible user GDPR location statuses:`enum class CheckGDPRLocationStatus` |
| [GDPRClickListener](-g-d-p-r-click-listener/index.html) | `interface GDPRClickListener` |
| [ICoreSplashFragment](-i-core-splash-fragment.html) | Used for Gdpr pop-up fragment, see [IGDPRConsent](-i-g-d-p-r-consent/index.html)`interface ICoreSplashFragment : `[`GDPRClickListener`](-g-d-p-r-click-listener/index.html) |
| [IGDPRConsent](-i-g-d-p-r-consent/index.html) | Its used for collect user consent for displaying ads, and ask them if we can provide personalized ads`interface IGDPRConsent : `[`IGDPRTexts`](-i-g-d-p-r-texts/index.html) |
| [IGDPRConsentConfig](-i-g-d-p-r-consent-config/index.html) | Configurations for Gdpr Consent, as for now only List of Admob publisher ids is needed`interface IGDPRConsentConfig` |
| [IGDPRTexts](-i-g-d-p-r-texts/index.html) | List of texts for Consent PopUp See implementations for more info`interface IGDPRTexts` |

