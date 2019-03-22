package pl.netigen.netigenapi;

import com.google.android.gms.ads.MobileAds;

public abstract class BaseLoaderSplashActivity extends BaseSplashActivity implements LoadProgressListener {
    private boolean loadingFinished;

    void initAdmob() {
        MobileAds.initialize(this, getAdmobAppID());
        admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        if (loadingFinished) {
            admobManager.splashScreenOnCreate(getIntentToLaunch());
        } else {
            admobManager.loadInterstitialIfNeeded(this);
        }
    }

    @Override
    public void onFinishLoading() {
        loadingFinished = true;
        if (consentFinished) {
            if (admobManager == null) {
                admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
            }
            admobManager.splashScreenOnCreate(getIntentToLaunch());
        }
    }
}
