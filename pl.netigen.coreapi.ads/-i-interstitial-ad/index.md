---
title: IInterstitialAd - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IInterstitialAd](./index.html)

# IInterstitialAd

`interface IInterstitialAd : `[`IAd`](../-i-ad/index.html)

Manages Interstitial Ad:

* loading
* showing to the user
* provides loading, showing and closing events
* is connected with activity lifecycle

Interstitial ads are full-screen ads that cover the interface of their host app.
They're typically displayed at natural transition points in the flow of an app,
such as between activities or during the pause between levels in a game.
In this api default behaviour we displaying it also after showing splash to the users on application launch.
When an app shows an interstitial ad,
the user has the choice to either tap on the ad and continue to its destination or close it and return to the app.

### Properties

| [isLoaded](is-loaded.html) | Indicates if ad is loaded and ready to show`abstract val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [load](load.html) | Loads ad and provides load success/failure callback`abstract fun load(): Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [loadIfShouldBeLoaded](load-if-should-be-loaded.html) | Loads ad if it is not currently loading or already loaded`abstract fun loadIfShouldBeLoaded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [showIfCanBeShowed](show-if-can-be-showed.html) | Shows ad if it is loaded and minimum time between this ads passed (limit can be ignored by [forceShow](show-if-can-be-showed.html#pl.netigen.coreapi.ads.IInterstitialAd$showIfCanBeShowed(kotlin.Boolean, kotlin.Function1((kotlin.Boolean, kotlin.Unit)))/forceShow) = true)`abstract fun showIfCanBeShowed(forceShow: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, onClosedOrNotShowed: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [AdMobInterstitial](../../pl.netigen.gms.ads/-ad-mob-interstitial/index.html) | [IInterstitialAd](./index.html) implementation with [InterstitialAd](#) from Google Mobile Ads SDK`class AdMobInterstitial : `[`IInterstitialAd`](./index.html)`, `[`LifecycleObserver`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleObserver.html) |

