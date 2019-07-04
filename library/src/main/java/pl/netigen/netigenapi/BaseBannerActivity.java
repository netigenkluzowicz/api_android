package pl.netigen.netigenapi;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
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
        if (getRewardedAdId() != null) {
            admobManager.createRewardedVideo(getRewardsListener());
            admobManager.loadRewardedVideo(getRewardedAdId());
        }
    }

    protected void initAdmobBanner() {
        if (!Config.isNoAdsBought()) {
            showBanner();
        }
    }

    private void showBanner() {
        bannerRelativeLayout = getBannerRelativeLayout();
        ViewGroup.LayoutParams layoutParams = bannerRelativeLayout.getLayoutParams();
        admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        admobManager.setIsMultiFullscreenApp(isMultiFullScreenApp());
        int bannerHeightPixels = AdSize.SMART_BANNER.getHeightInPixels(this);
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = bannerHeightPixels;
        bannerRelativeLayout.setGravity(Gravity.TOP);
        bannerRelativeLayout.setLayoutParams(layoutParams);
        admobManager.bannerActivityOnCreate();
    }

    private void hideBanner() {
        bannerRelativeLayout = getBannerRelativeLayout();
        ViewGroup.LayoutParams layoutParams = bannerRelativeLayout.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = 1;
        bannerRelativeLayout.setGravity(Gravity.TOP);
        bannerRelativeLayout.setLayoutParams(layoutParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.isNoAdsBought()) {
            hideBanner();
        } else {
            if (getRewardedAdId() != null) {
                admobManager.getRewardedVideoAd().resume(this);
            }
            onBannerAdResume();
        }
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
        if (getRewardedAdId() != null) {
            admobManager.getRewardedVideoAd().pause(this);
        }
    }

    public void onBannerAdPause() {
        if (admobManager != null) {
            admobManager.onBannerAdPause();
        }
    }

    public void turnOffAds() {
        Config.setNoAdsBought(true);
        admobManager = AdmobManager.createNoAdsInstance();
        hideBanner();
    }

    public AdmobManager getAdmobManager() {
        if (admobManager == null) {
            admobManager = AdmobManager.createNoAdsInstance();
        }
        return admobManager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getRewardedAdId() != null) {
            admobManager.getRewardedVideoAd().destroy(this);
        }
    }
}
