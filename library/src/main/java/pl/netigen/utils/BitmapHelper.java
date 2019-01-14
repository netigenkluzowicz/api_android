package pl.netigen.utils;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.IOException;


@SuppressWarnings("SameParameterValue")
public class BitmapHelper {

    public static Bitmap decodeBitmap(int resId, Resources resources) {
        return BitmapFactory.decodeResource(resources, resId);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, float scale) {
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * scale), (int) (bitmap.getHeight() * scale), true);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int widthPx, int heightPx) {
        if (bitmap != null) {
            return Bitmap.createScaledBitmap(bitmap, widthPx, heightPx, true);
        }
        return null;
    }

    public static Bitmap loadAndScaleBitmap(int resId, int widthPx, int heightPx, Resources resources) {
        return Bitmap.createScaledBitmap(decodeBitmap(resId, resources), widthPx, heightPx, true);
    }

    public static Bitmap loadAndScaleBitmap(int resId, float scale, Resources resources) {
        return scaleBitmap(decodeBitmap(resId, resources), scale);
    }

    public static Bitmap prepareScaledBitmap(int resId, float scale, Resources resources) {
        if (scale <= 0f) {
            throw new IllegalArgumentException("scale must be > 0");
        }
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        if (scale > 0.5f) {
            bitmap = decodeBitmap(resId, resources);
        } else {
            options.inSampleSize = calculateSampleSize(scale);
            bitmap = BitmapFactory.decodeResource(resources, resId, options);
        }
        if (scale != 1f) {
            bitmap = scaleBitmap(bitmap, scale * options.inSampleSize);
        }
        return bitmap;
    }

    private static int calculateSampleSize(float scale) {
        if (scale <= 0f || scale > 0.5f) {
            return 1;
        }
        int sampleSize = 1;
        while (0.5f / sampleSize >= scale) {
            sampleSize *= 2;
        }
        return sampleSize;
    }

    /**
     * Creates a new bitmap by flipping the specified bitmap
     * vertically or horizontally.
     *
     * @param src  Bitmap to flip
     * @param type Flip direction (horizontal or vertical)
     * @return New bitmap created by flipping the given one
     * vertically or horizontally as specified by
     * the <code>type</code> parameter or
     * the original bitmap if an unknown type
     * is specified.
     **/
    public static Bitmap flip(Bitmap src, Direction type) {
        Matrix matrix = new Matrix();

        if (type == Direction.VERTICAL) {
            matrix.preScale(1.0f, -1.0f);
        } else if (type == Direction.HORIZONTAL) {
            matrix.preScale(-1.0f, 1.0f);
        } else {
            return src;
        }

        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    private static Bitmap prepareBitmapInPx(int resId, int widthPx, int heightPx, Resources resources) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);

        // Calculate inSampleSize

        options.inSampleSize = calculateInSampleSize(options, widthPx, heightPx);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        final Bitmap src = BitmapFactory.decodeResource(resources, resId, options);
        if (src == null) return null;
        return Bitmap.createScaledBitmap(src, widthPx, heightPx, true);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw bitmapHeight and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // bitmapHeight and width larger than the requested bitmapHeight and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap loadAndScaleBitmap(AssetManager assets, String iconLink, int width, int height) {
        return scaleBitmap(getBitmapFromAsset(assets, iconLink), width, height);
    }

    public static Bitmap getBitmapFromAsset(AssetManager assets, String filePath) {
        try {
            return BitmapFactory.decodeStream(assets.open(filePath));
        } catch (IOException e) {
            return null;
        }
    }

    public enum Direction {VERTICAL, HORIZONTAL}
}
