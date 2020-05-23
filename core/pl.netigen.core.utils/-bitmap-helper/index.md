---
title: BitmapHelper - core
---

[api_android](../index.md)/[core](../../index.md) / [pl.netigen.core.utils](../index.md) / [BitmapHelper](./index.md)

# BitmapHelper

`open class BitmapHelper`

### Types

| [Direction](-direction/index.md) | `class Direction` |

### Constructors

| [&lt;init&gt;](-init-.html) | `BitmapHelper()` |

### Functions

| [decodeBitmap](decode-bitmap.html) | `open static fun decodeBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, resources: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.html)`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!` |
| [flip](flip.html) | Creates a new bitmap by flipping the specified bitmap vertically or horizontally.`open static fun flip(src: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!, type: Direction!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!` |
| [getBitmapFromAsset](get-bitmap-from-asset.html) | `open static fun getBitmapFromAsset(assets: `[`AssetManager`](https://developer.android.com/reference/android/content/res/AssetManager.html)`!, filePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!` |
| [loadAndScaleBitmap](load-and-scale-bitmap.html) | `open static fun loadAndScaleBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, widthPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, heightPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, resources: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.html)`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!`<br>`open static fun loadAndScaleBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.md)`, resources: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.html)`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!`<br>`open static fun loadAndScaleBitmap(assets: `[`AssetManager`](https://developer.android.com/reference/android/content/res/AssetManager.html)`!, iconLink: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!` |
| [prepareScaledBitmap](prepare-scaled-bitmap.html) | `open static fun prepareScaledBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.md)`, resources: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.html)`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!` |
| [scaleBitmap](scale-bitmap.html) | `open static fun scaleBitmap(bitmap: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.md)`): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!`<br>`open static fun scaleBitmap(bitmap: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!, widthPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, heightPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!` |

