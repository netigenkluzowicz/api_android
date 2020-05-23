---
title: IBannerAd - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.ads](../index.md) / [IBannerAd](./index.md)

# IBannerAd

`interface IBannerAd : `[`IAd`](../-i-ad/index.md)

Manages Banner ad:

* lifecycle is connected to activity lifecycle
* is placed automatically in given [RelativeLayout](https://developer.android.com/reference/android/widget/RelativeLayout.md)) provided in [IAdsConfig.bannerLayoutIdName](../-i-ads-config/banner-layout-id-name.md))
* its container is resized when ad size is different than its height, and it is invisible to the user

Banner ads (excluding Adaptive Banners) occupy a spot within an app's layout, either at the top or bottom of the device screen.
They stay on screen while users are interacting with the app, and can refresh automatically after a certain period of time.

Adaptive banners are the next generation of responsive ads, maximizing performance by optimizing ad size for each device.
Improving on smart banners, which only supported fixed heights,
adaptive banners let developers specify the ad-width and use this to determine the optimal ad size.

### Functions

| [getHeightInPixels](get-height-in-pixels.md)) | Measures current banner implementation exact height in pixels`abstract fun getHeightInPixels(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |

