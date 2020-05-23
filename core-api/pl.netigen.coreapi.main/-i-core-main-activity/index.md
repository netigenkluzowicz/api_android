---
title: ICoreMainActivity - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.main](../index.md) / [ICoreMainActivity](./index.md)

# ICoreMainActivity

`interface ICoreMainActivity`

Base and by design should be only Activity in application:

* observes when splash was showing/closing, and calls [onSplashOpened](on-splash-opened.html)/[onSplashClosed](on-splash-closed.html)
* observes when ads should be hided/showed and calls [showAds](show-ads.html)/[hideAds](hide-ads.html) and [onNoAdsChanged](on-no-ads-changed.html)
* shows GDPR pop up on [ICoreMainVM.resetAdsPreferences](../-i-core-main-v-m/reset-ads-preferences.html) called

### Properties

| [canCommitFragments](can-commit-fragments.html) | Indicates if we can safe perform Fragment transaction as [commit()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentTransaction.html#commit()) or [popBackStack()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager.html#popBackStack()) and others otherwise it will result with [IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.html) crash`abstract val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [coreMainVM](core-main-v-m.html) | `abstract val coreMainVM: `[`ICoreMainVM`](../-i-core-main-v-m/index.md) |
| [noAdsActive](no-ads-active.html) | Returns if no ads in-app purchase is active or inactive, its current value of [INoAds.noAdsActive](../../pl.netigen.coreapi.payments/-i-no-ads/no-ads-active.html), and should be used only when observing this value is hard to implement`abstract val noAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [splashActive](splash-active.html) | Indicates if [CoreSplashFragment](../../pl.netigen.coreapi.gdpr/-i-core-splash-fragment.html) is visible to the user`abstract val splashActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [viewModelFactory](view-model-factory.html) | Factory for providing [ICoreMainVM](../-i-core-main-v-m/index.md) and [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.md)`abstract val viewModelFactory: `[`ICoreViewModelsFactory`](../-i-core-view-models-factory/index.md) |

### Functions

| [hideAds](hide-ads.html) | Called when [IBannerAd](../../pl.netigen.coreapi.ads/-i-banner-ad/index.md) and other ads related views should be hided`abstract fun hideAds(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onNoAdsChanged](on-no-ads-changed.html) | Called when no-ads payment is active or not this as in [INoAds.noAdsActive](../../pl.netigen.coreapi.payments/-i-no-ads/no-ads-active.html)`abstract fun onNoAdsChanged(noAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onSplashClosed](on-splash-closed.html) | It's called when [CoreSplashFragment](../../pl.netigen.coreapi.gdpr/-i-core-splash-fragment.html) is closed`abstract fun onSplashClosed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onSplashOpened](on-splash-opened.html) | It's called when [CoreSplashFragment](../../pl.netigen.coreapi.gdpr/-i-core-splash-fragment.html) is showed`abstract fun onSplashOpened(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showAds](show-ads.html) | Called when [IBannerAd](../../pl.netigen.coreapi.ads/-i-banner-ad/index.md) and other ads related views should be showed`abstract fun showAds(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showGdprPopUp](show-gdpr-pop-up.html) | Shows GDPR pop up on [ICoreMainVM.resetAdsPreferences](../-i-core-main-v-m/reset-ads-preferences.html) called`abstract fun showGdprPopUp(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

