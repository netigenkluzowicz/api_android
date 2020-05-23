---
title: CoreMainActivity - api_android
---

[api_android](../../index.html) / [pl.netigen.core.main](../index.html) / [CoreMainActivity](./index.html)

# CoreMainActivity

`abstract class CoreMainActivity : `[`AppCompatActivity`](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)`, `[`ICoreMainActivity`](../../pl.netigen.coreapi.main/-i-core-main-activity/index.html)

Implements [ICoreMainActivity](../../pl.netigen.coreapi.main/-i-core-main-activity/index.html)

### Constructors

| [&lt;init&gt;](-init-.html) | Implements [ICoreMainActivity](../../pl.netigen.coreapi.main/-i-core-main-activity/index.html)`CoreMainActivity()` |

### Properties

| [canCommitFragments](can-commit-fragments.html) | Indicates if we can safe perform Fragment transaction as [commit()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentTransaction.html#commit()) or [popBackStack()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager.html#popBackStack()) and others otherwise it will result with [IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.html) crash`open val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [coreMainVM](core-main-v-m.html) | `open val coreMainVM: `[`ICoreMainVM`](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) |
| [noAdsActive](no-ads-active.html) | Returns if no ads in-app purchase is active or inactive, its current value of [INoAds.noAdsActive](../../pl.netigen.coreapi.payments/-i-no-ads/no-ads-active.html), and should be used only when observing this value is hard to implement`open val noAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [rateUs](rate-us.html) | `val rateUs: `[`RateUs`](../../pl.netigen.core.rateus/-rate-us/index.html) |
| [splashActive](splash-active.html) | Indicates if [CoreSplashFragment](../../pl.netigen.coreapi.gdpr/-i-core-splash-fragment.html) is visible to the user`open val splashActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [onActivityResult](on-activity-result.html) | `open fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.html) | Starts observing [ICoreMainVM.noAdsActive](#) and [ICoreMainVM.showGdprResetAds](../../pl.netigen.coreapi.main/-i-core-main-v-m/show-gdpr-reset-ads.html)`open fun onCreate(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onNoAdsChanged](on-no-ads-changed.html) | Called when no-ads payment is active or not this as in [INoAds.noAdsActive](../../pl.netigen.coreapi.payments/-i-no-ads/no-ads-active.html)`open fun onNoAdsChanged(noAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSplashClosed](on-splash-closed.html) | It's called when [CoreSplashFragment](../../pl.netigen.core.splash/-core-splash-fragment/index.html) is closed If [CoreMainVM.useDefaultRateUs](#) is true check for Rate Us show, see: [IRateUs](../../pl.netigen.coreapi.rateus/-i-rate-us/index.html)`open fun onSplashClosed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSplashOpened](on-splash-opened.html) | It's called when [CoreSplashFragment](../../pl.netigen.coreapi.gdpr/-i-core-splash-fragment.html) is showed`open fun onSplashOpened(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onStart](on-start.html) | `open fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [showGdprPopUp](show-gdpr-pop-up.html) | Shows GDPR pop up on [ICoreMainVM.resetAdsPreferences](../../pl.netigen.coreapi.main/-i-core-main-v-m/reset-ads-preferences.html) called`open fun showGdprPopUp(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

