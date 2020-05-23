---
title: IAdsConfig.inDebugMode - core-api
---

[api_android](../index.md)/[core-api](../../index.md)/[pl.netigen.coreapi.ads](../index.md)/[IAdsConfig](index.md)/[inDebugMode](./in-debug-mode.html)

# inDebugMode

`abstract val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)

Sets debug mode on/off, when:

* true - test ads Ids provided by Admob or HMS Ads will be used on all devices
* false - production ads will be displayed excluding test devices provided in [testDevices](test-devices.html)
