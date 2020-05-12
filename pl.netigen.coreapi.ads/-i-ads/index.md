[core-api](../../index.md) / [pl.netigen.coreapi.ads](../index.md) / [IAds](./index.md)

# IAds

`interface IAds`

### Properties

| [bannerAd](banner-ad.md) | `abstract val bannerAd: `[`IBannerAd`](../-i-banner-ad/index.md) |
| [interstitialAd](interstitial-ad.md) | `abstract val interstitialAd: `[`IInterstitialAd`](../-i-interstitial-ad/index.md) |
| [personalizedAdsEnabled](personalized-ads-enabled.md) | `abstract var personalizedAdsEnabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [rewardedAd](rewarded-ad.md) | `abstract val rewardedAd: `[`IRewardedAd`](../-i-rewarded-ad/index.md) |

### Functions

| [disable](disable.md) | `abstract fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [enable](enable.md) | `abstract fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.md) | `interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](./index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md) |

