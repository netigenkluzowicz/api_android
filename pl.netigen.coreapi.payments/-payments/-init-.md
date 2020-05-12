---
title: Payments.<init> - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.payments](../index.html) / [Payments](index.html) / [&lt;init&gt;](./-init-.html)

# &lt;init&gt;

`Payments(paymentsImplContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = paymentsImplContext.packageName, noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = listOf("${packageName}.noads"), inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = listOf("${packageName}.noads"))`

Base class for [IPayments](../-i-payments/index.html) implementations

Provides also access to payments repository and default sku values

