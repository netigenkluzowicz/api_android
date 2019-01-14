package pl.netigen.netigenapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdSize;

public abstract class BaseBannerActivity extends AppCompatActivity implements AdmobIds {
    private AdmobManager admobManager;
    private RelativeLayout bannerRelativeLayout;

    public abstract RelativeLayout getBannerRelativeLayout();

    public abstract int getContentViewID();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewID());
        initAdmobBanner();
    }

    protected void initAdmobBanner() {
        admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        admobManager.setIsMultiFullscreenApp(isMultiFullScreenApp());
        bannerRelativeLayout = getBannerRelativeLayout();
        ViewGroup.LayoutParams layoutParams = bannerRelativeLayout.getLayoutParams();
        if (!admobManager.isNoAdsBought()) {
            int bannerHeightPixels = AdSize.SMART_BANNER.getHeightInPixels(this);
            layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            layoutParams.height = bannerHeightPixels;
            bannerRelativeLayout.setGravity(Gravity.TOP);
            bannerRelativeLayout.setLayoutParams(layoutParams);
        } else {
            onNoAdsBought(layoutParams);
        }
        admobManager.bannerActivityOnCreate();
    }

    private void onNoAdsBought(ViewGroup.LayoutParams layoutParams) {
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = 1;
        bannerRelativeLayout.setGravity(Gravity.TOP);
        bannerRelativeLayout.setLayoutParams(layoutParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
        admobManager.onBannerAdResume(bannerRelativeLayout);
    }


    @Override
    protected void onPause() {
        super.onPause();
        admobManager.onBannerAdPause();
    }

    public void onBannerAdPause() {
        admobManager.onBannerAdPause();
    }


    public AdmobManager getAdmobManager() {
        return admobManager;
    }
}
