---
title: CoreMainActivity - core
---

[core](../../index.md) / [pl.netigen.core.main](../index.md) / [CoreMainActivity](./index.md)

# CoreMainActivity

`abstract class CoreMainActivity : `[`AppCompatActivity`](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatActivity.md))`, ICoreMainActivity`

Implements [ICoreMainActivity](#)

### Constructors

| [&lt;init&gt;](-init-.md)) | Implements [ICoreMainActivity](#)`CoreMainActivity()` |

### Properties

| [canCommitFragments](can-commit-fragments.md)) | `open val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [coreMainVM](core-main-v-m.md)) | `open val coreMainVM: ICoreMainVM` |
| [noAdsActive](no-ads-active.md)) | `open val noAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [rateUs](rate-us.md)) | `val rateUs: `[`RateUs`](../../pl.netigen.core.rateus/-rate-us/index.md) |
| [splashActive](splash-active.md)) | `open val splashActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [onActivityResult](on-activity-result.md)) | `open fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, data: `[`Intent`](https://developer.android.com/reference/android/content/Intent.md))`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onCreate](on-create.md)) | Starts observing [ICoreMainVM.noAdsActive](#) and [ICoreMainVM.showGdprResetAds](#)`open fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.md))`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onNoAdsChanged](on-no-ads-changed.md)) | `open fun onNoAdsChanged(noAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onSplashClosed](on-splash-closed.md)) | It's called when [CoreSplashFragment](../../pl.netigen.core.splash/-core-splash-fragment/index.md) is closed If [CoreMainVM.useDefaultRateUs](#) is true check for Rate Us show, see: [IRateUs](#)`open fun onSplashClosed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onSplashOpened](on-splash-opened.md)) | `open fun onSplashOpened(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onStart](on-start.md)) | `open fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showGdprPopUp](show-gdpr-pop-up.md)) | `open fun showGdprPopUp(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

