---
title: pl.netigen.coreapi.gdpr - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../index.md) / [pl.netigen.coreapi.gdpr](./index.md)

## Package pl.netigen.coreapi.gdpr

### Types

| [AdConsentStatus](-ad-consent-status/index.md) | Possible values of Gdpr ads consent status`enum class AdConsentStatus` |
| [CheckGDPRLocationStatus](-check-g-d-p-r-location-status/index.md) | Possible user GDPR location statuses:`enum class CheckGDPRLocationStatus` |
| [GDPRClickListener](-g-d-p-r-click-listener/index.md) | `interface GDPRClickListener` |
| [ICoreSplashFragment](-i-core-splash-fragment.html) | Used for Gdpr pop-up fragment, see [IGDPRConsent](-i-g-d-p-r-consent/index.md)`interface ICoreSplashFragment : `[`GDPRClickListener`](-g-d-p-r-click-listener/index.md) |
| [IGDPRConsent](-i-g-d-p-r-consent/index.md) | Its used for collect user consent for displaying ads, and ask them if we can provide personalized ads`interface IGDPRConsent : `[`IGDPRTexts`](-i-g-d-p-r-texts/index.md) |
| [IGDPRConsentConfig](-i-g-d-p-r-consent-config/index.md) | Configurations for Gdpr Consent, as for now only List of Admob publisher ids is needed`interface IGDPRConsentConfig` |
| [IGDPRTexts](-i-g-d-p-r-texts/index.md) | List of texts for Consent PopUp See implementations for more info`interface IGDPRTexts` |

