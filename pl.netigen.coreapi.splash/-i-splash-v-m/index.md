[core-api](../../index.md) / [pl.netigen.coreapi.splash](../index.md) / [ISplashVM](./index.md)

# ISplashVM

`interface ISplashVM : `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md)

### Properties

| [gdprConsent](gdpr-consent.md) | `abstract val gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md) |
| [isFirstLaunch](is-first-launch.md) | `abstract val isFirstLaunch: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [splashState](splash-state.md) | `abstract val splashState: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`SplashState`](../-splash-state/index.md)`>` |

### Functions

| [setPersonalizedAds](set-personalized-ads.md) | `abstract fun setPersonalizedAds(personalizedAdsApproved: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.md) | `abstract fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [SplashVM](../-splash-v-m/index.md) | `abstract class SplashVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ISplashVM`](./index.md) |

