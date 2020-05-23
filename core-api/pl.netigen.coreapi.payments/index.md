---
title: pl.netigen.coreapi.payments - core-api
---

[api_android](../index.md)/[core-api](../index.md)/[pl.netigen.coreapi.payments](./index.md)

## Package pl.netigen.coreapi.payments

### Types

| [INoAds](-i-no-ads/index.md) | Interface for no ads Payments, which turns on/off ads in whole application`interface INoAds` |
| [IPayments](-i-payments/index.md) | Interface for payments, extends [INoAds](-i-no-ads/index.md)`interface IPayments : `[`INoAds`](-i-no-ads/index.md) |
| [IPaymentsRepo](-i-payments-repo/index.md) | Interface for current payments repository implementation`interface IPaymentsRepo` |
| [NoAdsNotAvailable](-no-ads-not-available/index.md) | [INoAds](-i-no-ads/index.md) implementation when we have no no-ads payment`object NoAdsNotAvailable : `[`INoAds`](-i-no-ads/index.md) |
| [Payments](-payments/index.md) | Base class for [IPayments](-i-payments/index.md) implementations`abstract class Payments : `[`IPayments`](-i-payments/index.md)`, `[`IPaymentsRepo`](-i-payments-repo/index.md) |
| [Security](-security/index.md) | `class Security` |

