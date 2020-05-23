---
title: BitmapHelper.flip - core
---

[core](../../index.md) / [pl.netigen.core.utils](../index.md) / [BitmapHelper](index.md) / [flip](./flip.md))

# flip

`open static fun flip(src: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!, type: Direction!): `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.md))`!`

Creates a new bitmap by flipping the specified bitmap vertically or horizontally.

### Parameters

`src` - [Bitmap](https://developer.android.com/reference/android/graphics/Bitmap.md))!: Bitmap to flip

`type` - Direction!: Flip direction (horizontal or vertical)

**Return**
[Bitmap](https://developer.android.com/reference/android/graphics/Bitmap.md))!: New bitmap created by flipping the given one vertically or horizontally as specified by the `type` parameter or the original bitmap if an unknown type is specified.

