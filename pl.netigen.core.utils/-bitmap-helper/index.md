---
title: BitmapHelper - api_android
---

[api_android](../../index.html) / [pl.netigen.core.utils](../index.html) / [BitmapHelper](./index.html)

# BitmapHelper

`open class BitmapHelper`

### Types

| [Direction](-direction/index.html) | `class Direction` |

### Constructors

| [&lt;init&gt;](-init-.html) | `BitmapHelper()` |

### Functions

| [decodeBitmap](decode-bitmap.html) | `open static fun decodeBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resources: Resources!): Bitmap!` |
| [flip](flip.html) | Creates a new bitmap by flipping the specified bitmap vertically or horizontally.`open static fun flip(src: Bitmap!, type: Direction!): Bitmap!` |
| [getBitmapFromAsset](get-bitmap-from-asset.html) | `open static fun getBitmapFromAsset(assets: AssetManager!, filePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!): Bitmap!` |
| [loadAndScaleBitmap](load-and-scale-bitmap.html) | `open static fun loadAndScaleBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, widthPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, heightPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resources: Resources!): Bitmap!`<br>`open static fun loadAndScaleBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`, resources: Resources!): Bitmap!`<br>`open static fun loadAndScaleBitmap(assets: AssetManager!, iconLink: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Bitmap!` |
| [prepareScaledBitmap](prepare-scaled-bitmap.html) | `open static fun prepareScaledBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`, resources: Resources!): Bitmap!` |
| [scaleBitmap](scale-bitmap.html) | `open static fun scaleBitmap(bitmap: Bitmap!, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`): Bitmap!`<br>`open static fun scaleBitmap(bitmap: Bitmap!, widthPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, heightPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Bitmap!` |

