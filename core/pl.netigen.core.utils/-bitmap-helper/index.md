---
title: BitmapHelper - core
---

[core](../../index.md) / [pl.netigen.core.utils](../index.md) / [BitmapHelper](./index.md)

# BitmapHelper

`open class BitmapHelper`

### Types

| [Direction](-direction/index.md) | `class Direction` |

### Constructors

| [&lt;init&gt;](-init-.md)) | `BitmapHelper()` |

### Functions

| [decodeBitmap](decode-bitmap.md)) | `open static fun decodeBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, resources: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.md))`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!` |
| [flip](flip.md)) | Creates a new bitmap by flipping the specified bitmap vertically or horizontally.`open static fun flip(src: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!, type: Direction!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!` |
| [getBitmapFromAsset](get-bitmap-from-asset.md)) | `open static fun getBitmapFromAsset(assets: `[`AssetManager`](https://developer.android.com/reference/android/content/res/AssetManager.md))`!, filePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!` |
| [loadAndScaleBitmap](load-and-scale-bitmap.md)) | `open static fun loadAndScaleBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, widthPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, heightPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, resources: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.md))`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!`<br>`open static fun loadAndScaleBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.md)`, resources: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.md))`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!`<br>`open static fun loadAndScaleBitmap(assets: `[`AssetManager`](https://developer.android.com/reference/android/content/res/AssetManager.md))`!, iconLink: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!, width: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, height: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!` |
| [prepareScaledBitmap](prepare-scaled-bitmap.md)) | `open static fun prepareScaledBitmap(resId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.md)`, resources: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.md))`!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!` |
| [scaleBitmap](scale-bitmap.md)) | `open static fun scaleBitmap(bitmap: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!, scale: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.md)`): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!`<br>`open static fun scaleBitmap(bitmap: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!, widthPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, heightPx: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!` |

