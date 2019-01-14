package pl.netigen.netigenapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class BitmapFileManager {
    private final Context context;

    public BitmapFileManager(Context context) {
        this.context = context;
    }

    public boolean saveIconBitmap(Bitmap image, String packageName) {
        try {
            FileOutputStream fos = context.openFileOutput(Const.ICONS_PATH + packageName, Context.MODE_PRIVATE);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Bitmap tryLoadIconBitmap(String packageName) {
        try {
            Bitmap bitmap;
            File filePath = context.getFileStreamPath(Const.ICONS_PATH + packageName);
            FileInputStream fi = new FileInputStream(filePath);
            bitmap = BitmapFactory.decodeStream(fi);
            return bitmap;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean saveAdBitmap(Bitmap image, String packageName) {
        try {
            FileOutputStream fos = context.openFileOutput(Const.ADS_PATH + packageName, Context.MODE_PRIVATE);
            image.compress(Bitmap.CompressFormat.JPEG, 95, fos);
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Bitmap tryLoadAdBitmap(String packageName) {
        try {
            Bitmap bitmap;
            File filePath = context.getFileStreamPath(Const.ADS_PATH + packageName);
            FileInputStream fi = new FileInputStream(filePath);
            bitmap = BitmapFactory.decodeStream(fi);
            return bitmap;
        } catch (Exception ex) {
            return null;
        }
    }
}
