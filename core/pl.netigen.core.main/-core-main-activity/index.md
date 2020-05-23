---
title: CoreMainActivity - core
---

[core](../../index.md) / [pl.netigen.core.main](../index.md) / [CoreMainActivity](./index.md)

# CoreMainActivity

`abstract class CoreMainActivity : `[`AppCompatActivity`](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)`, ICoreMainActivity`

Implements [ICoreMainActivity](#)

### Constructors

| [&lt;init&gt;](-init-.html) | Implements [ICoreMainActivity](#)`CoreMainActivity()` |

### Properties

| [canCommitFragments](can-commit-fragments.html) | `open val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [coreMainVM](core-main-v-m.html) | `open val coreMainVM: ICoreMainVM` |
| [noAdsActive](no-ads-active.html) | `open val noAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [rateUs](rate-us.html) | `val rateUs: `[`RateUs`](../../pl.netigen.core.rateus/-rate-us/index.md) |
| [splashActive](splash-active.html) | `open val splashActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [onActivityResult](on-activity-result.html) | `open fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, data: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onCreate](on-create.html) | Starts observing [ICoreMainVM.noAdsActive](#) and [ICoreMainVM.showGdprResetAds](#)`open fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onNoAdsChanged](on-no-ads-changed.html) | `open fun onNoAdsChanged(noAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onSplashClosed](on-splash-closed.html) | It's called when [CoreSplashFragment](../../pl.netigen.core.splash/-core-splash-fragment/index.md) is closed If [CoreMainVM.useDefaultRateUs](#) is true check for Rate Us show, see: [IRateUs](#)`open fun onSplashClosed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onSplashOpened](on-splash-opened.html) | `open fun onSplashOpened(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onStart](on-start.html) | `open fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showGdprPopUp](show-gdpr-pop-up.html) | `open fun showGdprPopUp(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

