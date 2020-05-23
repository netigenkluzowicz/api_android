---
title: IAppConfig.inDebugMode - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.main](../index.md) / [IAppConfig](index.md) / [inDebugMode](./in-debug-mode.html)

# inDebugMode

`abstract val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)

Sets debug mode on/off, when:

* true - test ads will be displayed, and GMS Payments will be showing Toast debug messages
* false - production ads will be displayed excluding test devices provided in [IAdsConfig.testDevices](../../pl.netigen.coreapi.ads/-i-ads-config/test-devices.html)
