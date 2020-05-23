---
title: CoreMainVM.<init> - core-api
---

[api_android](../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.main](../index.md) / [CoreMainVM](index.md) / [&lt;init&gt;](./-init-.html)

# &lt;init&gt;

`CoreMainVM(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)`

Base class for [ICoreMainVM](../-i-core-main-v-m/index.md) implementation, extends [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) because it must be available from any Fragment or Activity

For easy access in fragments use [NetigenVMFragment](#)

see: [ICoreMainVM](../-i-core-main-v-m/index.md), [ICoreMainActivity](../-i-core-main-activity/index.md)

### Parameters

`application` - [Application](https://developer.android.com/reference/android/app/Application.html)  context of this class