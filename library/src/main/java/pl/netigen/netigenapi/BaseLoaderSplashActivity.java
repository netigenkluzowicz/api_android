package pl.netigen.netigenapi;

import com.google.android.gms.ads.MobileAds;

public abstract class BaseLoaderSplashActivity extends BaseSplashActivity implements LoadProgressListener {
    private boolean loadingFinished;

    void initAdmob() {
        MobileAds.initialize(this, getAdmobAppID());
        setAdmobManager(AdmobManager.create(getBannerId(), getFullScreenId(), this));
        if (loadingFinished) {
            getAdmobManager().splashScreenOnCreate(getIntentToLaunch());
        } else {
            getAdmobManager().loadInterstitialIfNeeded(this);
        }
    }

    @Override
    public void onFinishLoading() {
        loadingFinished = true;
        if (getConsentFinished()) {
            if (getAdmobManager() == null) {
                setAdmobManager(AdmobManager.create(getBannerId(), getFullScreenId(), this));
            }
            getAdmobManager().splashScreenOnCreate(getIntentToLaunch());
        }
    }
}
