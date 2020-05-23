---
title: AppConfig.inDebugMode - api_android
---

[api_android](../../index.html) / [pl.netigen.core.config](../index.html) / [AppConfig](index.html) / [inDebugMode](./in-debug-mode.html)

# inDebugMode

`open val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Sets debug mode on/off, when:

* true - test ads will be displayed, and GMS Payments will be showing Toast debug messages
* false - production ads will be displayed excluding test devices provided in [IAdsConfig.testDevices](../../pl.netigen.coreapi.ads/-i-ads-config/test-devices.html)
