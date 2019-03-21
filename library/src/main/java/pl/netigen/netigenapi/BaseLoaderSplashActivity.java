package pl.netigen.netigenapi;

import com.google.android.gms.ads.MobileAds;

public abstract class BaseLoaderSplashActivity extends BaseSplashActivity implements LoadProgressListener {

    void initAdmob() {
        MobileAds.initialize(this, getAdmobAppID());
        admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        admobManager.loadInterstitialIfNeeded(this);
    }

    @Override
    public void onFinishLoading() {
        if (admobManager == null) {
            admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        }
        admobManager.splashScreenOnCreate(getIntentToLaunch());
    }
}
