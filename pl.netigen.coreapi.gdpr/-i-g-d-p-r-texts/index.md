---
title: IGDPRTexts - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.gdpr](../index.html) / [IGDPRTexts](./index.html)

# IGDPRTexts

`interface IGDPRTexts`

List of texts for Consent PopUp
See implementations for more info

### Properties

| [text1](text1.html) | `abstract val text1: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [text2](text2.html) | `abstract val text2: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [text3](text3.html) | `abstract val text3: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [text4](text4.html) | `abstract val text4: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [text5](text5.html) | `abstract val text5: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [textPolicy1](text-policy1.html) | `abstract val textPolicy1: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [textPolicy2](text-policy2.html) | `abstract val textPolicy2: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| [ConstGDPR](../../pl.netigen.gms.gdpr/-const-g-d-p-r/index.html) | `object ConstGDPR : `[`IGDPRTexts`](./index.html) |
| [GDPRConsentImpl](../../pl.netigen.gms.gdpr/-g-d-p-r-consent-impl/index.html) | [IGDPRConsent](../-i-g-d-p-r-consent/index.html) implementation with using [googleads-consent-sdk-android](https://github.com/googleads/googleads-consent-sdk-android)`class GDPRConsentImpl : `[`IGDPRConsent`](../-i-g-d-p-r-consent/index.html)`, `[`IGDPRTexts`](./index.html) |
| [IGDPRConsent](../-i-g-d-p-r-consent/index.html) | Its used for collect user consent for displaying ads, and ask them if we can provide personalized ads`interface IGDPRConsent : `[`IGDPRTexts`](./index.html) |

