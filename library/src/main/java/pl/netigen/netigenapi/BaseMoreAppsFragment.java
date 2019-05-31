package pl.netigen.netigenapi;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.netigen.utils.BitmapHelper;


public abstract class BaseMoreAppsFragment extends Fragment {
    public static final String MOREAPPS = "moreapps/";
    public static final String PNG = ".png";
    public static final String UTF_8 = "UTF-8";
    public static final float WIDTH_SCALE = 0.4f;
    private static final String MOREAPPS_JSON = "moreapps.json";

    public float getWidthScale() {
        return WIDTH_SCALE;
    }

    private void updateMoreApps(List<ImageView> imageViews, Activity activity) {
        try {
            Reader reader = new InputStreamReader(activity.getAssets().open(MOREAPPS_JSON), UTF_8);
            List<MoreAppItem> listApps = (new Gson()).fromJson(reader, TypeToken.getParameterized(ArrayList.class, MoreAppItem.class).getType());
            loadLocalMoreApps(imageViews, activity, listApps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLocalMoreApps(List<ImageView> imageViews, Activity activity, List<MoreAppItem> listApps) throws IOException {
        for (int i = 0; i < listApps.size(); i++) {
            final MoreAppItem moreAppItem = listApps.get(i);
            String path = getMoreAppsAssetsFolderPath() + moreAppItem.packageName + PNG;
            final BitmapFactory.Options options = new BitmapFactory.Options();
            AssetManager assets = activity.getAssets();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(assets.open(path), null, options);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float targetWidth = displayMetrics.widthPixels * getWidthScale();
            float scale = targetWidth / options.outWidth;
            Bitmap imageBitmap = BitmapHelper.loadAndScaleBitmap(assets, path, (int) (options.outWidth * scale), (int) (options.outHeight * scale));
            imageViews.get(i).setImageBitmap(imageBitmap);
            imageViews.get(i).setOnClickListener(v -> Utils.openMarketLink(activity, moreAppItem.packageName));
        }
    }

    public String getMoreAppsAssetsFolderPath() {
        return MOREAPPS;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        updateMoreApps(getImageViews(view), getActivity());
        return view;
    }

    private List<ImageView> getImageViews(View v) {
        if (provideManuallyImageViews() == null) {
            List<View> allChildren = getAllChildren(v);
            List<ImageView> imageViews = new ArrayList<>();
            for (View view : allChildren) {
                if (view instanceof ImageView) {
                    imageViews.add((ImageView) view);
                }
            }
            return imageViews;
        }
        return Arrays.asList(provideManuallyImageViews());
    }

    private List<View> getAllChildren(View v) {
        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }
        ArrayList<View> result = new ArrayList<>();
        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            result.addAll(getAllChildren(child));
        }
        return result;
    }

    public ImageView[] provideManuallyImageViews() {
        return null;
    }

    public abstract int getLayoutId();
}
