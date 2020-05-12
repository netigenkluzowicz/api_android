---
title: pl.netigen.coreapi.payments - core-api
---

[core-api](../index.html) / [pl.netigen.coreapi.payments](./index.html)

## Package pl.netigen.coreapi.payments

### Types

| [INoAds](-i-no-ads/index.html) | Interface for no ads Payments, which turns on/off ads in whole application`interface INoAds` |
| [IPayments](-i-payments/index.html) | Interface for payments, extends [INoAds](-i-no-ads/index.html)`interface IPayments : `[`INoAds`](-i-no-ads/index.html) |
| [IPaymentsRepo](-i-payments-repo/index.html) | Interface for current payments repository implementation`interface IPaymentsRepo` |
| [NoAdsNotAvailable](-no-ads-not-available/index.html) | [INoAds](-i-no-ads/index.html) implementation when we have no no-ads payment`object NoAdsNotAvailable : `[`INoAds`](-i-no-ads/index.html) |
| [Payments](-payments/index.html) | Base class for [IPayments](-i-payments/index.html) implementations`abstract class Payments : `[`IPayments`](-i-payments/index.html) |
| [Security](-security/index.html) | `class Security` |

