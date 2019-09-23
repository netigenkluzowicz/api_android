package pl.netigen.core.moreapps;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import pl.netigen.netigenapi.Const;
import pl.netigen.netigenapi.R;
import pl.netigen.utils.BitmapHelper;

import static android.content.Context.MODE_PRIVATE;

public class MoreAppItem extends AppInfo {
    private Bitmap iconBitmap;

    public MoreAppItem(String packageName, String appName, String iconLink, String fullAdLink, boolean isAd, int version) {
        super(packageName, appName, iconLink, fullAdLink, isAd, version);

        isAd = false;
    }

    public MoreAppItem(AppInfo appInfo) {
        super(appInfo.packageName, appInfo.appName, appInfo.iconLink, appInfo.fullAdLink, false, appInfo.version);

    }

    public Bitmap getIconBitmap(Resources resources) {
        if (iconBitmap == null) {
            loadIconBitmap(resources);
        }
        return iconBitmap;
    }

    public Bitmap getIconBitmap(Resources resources, Activity activity) {
        if (iconBitmap == null) {
            loadIconBitmap(resources, activity);
        }
        return iconBitmap;
    }

    private void loadIconBitmap(Resources resources, Activity activity) {
        int size = resources.getDimensionPixelSize(R.dimen.moreAppIconSize);
        SharedPreferences sharedPref = activity.getSharedPreferences(Const.MOREAPPS, MODE_PRIVATE);
        String path = sharedPref.getString(packageName, null);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        iconBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
    }

    private void loadIconBitmap(Resources resources) {
        int size = resources.getDimensionPixelSize(R.dimen.moreAppIconSize);
        iconBitmap = BitmapHelper.loadAndScaleBitmap(resources.getAssets(), "moreapps/" + packageName + ".png", size, size);
    }

    public void setIconBitmap(Bitmap iconBitmap) {
        this.iconBitmap = iconBitmap;
    }
}
