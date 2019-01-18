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
        bannerRelativeLayout = getBannerRelativeLayout();
        ViewGroup.LayoutParams layoutParams = bannerRelativeLayout.getLayoutParams();
        if (Config.isNoAdsBought()) {
            hideBanner(layoutParams);
        } else {
            showBanner(layoutParams);
        }
    }

    private void showBanner(ViewGroup.LayoutParams layoutParams) {
        admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        admobManager.setIsMultiFullscreenApp(isMultiFullScreenApp());
        int bannerHeightPixels = AdSize.SMART_BANNER.getHeightInPixels(this);
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = bannerHeightPixels;
        bannerRelativeLayout.setGravity(Gravity.TOP);
        bannerRelativeLayout.setLayoutParams(layoutParams);
        admobManager.bannerActivityOnCreate();
    }

    private void hideBanner(ViewGroup.LayoutParams layoutParams) {
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = 1;
        bannerRelativeLayout.setGravity(Gravity.TOP);
        bannerRelativeLayout.setLayoutParams(layoutParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onBannerAdResume();
    }

    protected void onBannerAdResume() {
        if (admobManager != null) {
            admobManager.onBannerAdResume(bannerRelativeLayout);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onBannerAdPause();
    }

    public void onBannerAdPause() {
        if (admobManager != null) {
            admobManager.onBannerAdPause();
        }
    }

    public void turnOffAds() {
        Config.setNoAdsBought(true);
        admobManager = AdmobManager.createNoAdsInstance();
        initAdmobBanner();
    }

    public AdmobManager getAdmobManager() {
        if (admobManager == null) {
            admobManager = AdmobManager.createNoAdsInstance();
        }
        return admobManager;
    }
}
