---
title: BitmapHelper.flip - core
---

[core](../../index.html) / [pl.netigen.core.utils](../index.html) / [BitmapHelper](index.html) / [flip](./flip.html)

# flip

`open static fun flip(src: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!, type: Direction!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`!`

Creates a new bitmap by flipping the specified bitmap vertically or horizontally.

### Parameters

`src` - [Bitmap](https://developer.android.com/reference/android/graphics/Bitmap.html)!: Bitmap to flip

`type` - Direction!: Flip direction (horizontal or vertical)

**Return**
[Bitmap](https://developer.android.com/reference/android/graphics/Bitmap.html)!: New bitmap created by flipping the given one vertically or horizontally as specified by the `type` parameter or the original bitmap if an unknown type is specified.

