package pl.netigen.netigenapi;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.android.gms.ads.MobileAds;

import pl.netigen.rodo.ConstRodo;
import pl.netigen.rodo.RodoFragment;


public abstract class BaseSplashActivity extends AppCompatActivity implements ISplashActivity, AdmobIds, RodoFragment.ClickListener {
    private static final String RODO_FRAGMENT_TAG = "rodo";
    private AdmobManager admobManager;
    private RodoFragment rodoFragment;
    private boolean canCommitFragment;
    private Handler initAdmobHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        Config.initialize(getConfigBuilder());
        if (!isNoAdsPaymentAvailable()) {
            Config.setNoAdsBought(false);
            showConsent();
        }
    }

    private void showConsent() {
        final ConsentInformation consentInformation = ConsentInformation.getInstance(this);
        consentInformation.requestConsentInfoUpdate(getPublisherIds(), new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                boolean isInEea = ConsentInformation.getInstance(BaseSplashActivity.this).isRequestLocationInEeaOrUnknown();
                ConstRodo.setIsInEea(isInEea);
                if (isInEea && consentInformation.getConsentStatus() == ConsentStatus.UNKNOWN) {
                    initRodoFragment();
                } else {
                    startAdmobSplash();
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                startAdmobSplash();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        canCommitFragment = false;
        if (admobManager != null) {
            admobManager.splashScreenOnStop();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        canCommitFragment = true;
        if (admobManager != null) {
            admobManager.splashScreenOnStart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (admobManager != null) {
            admobManager.splashScreenOnDestroy();
        }
    }

    private void initRodoFragment() {
        if (canCommitFragment) {
            rodoFragment = RodoFragment.newInstance();
            rodoFragment.setIsPayOptions(isNoAdsPaymentAvailable());
            getSupportFragmentManager().beginTransaction()
                    .add(getSplashFragmentRodoContainerId(), rodoFragment, RODO_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }

    private void startAdmobSplash() {
        if (initAdmobHandler == null) {
            initAdmobHandler = new Handler();
            initAdmobHandler.post(this::initAdmob);
        }
    }

    private void initAdmob() {
        MobileAds.initialize(this, getAdmobAppID());
        admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        admobManager.splashScreenOnCreate(getIntentToLaunch());
    }

    @Override
    public void onBackPressed() {
        if (rodoFragment != null) {
            rodoFragment.showAdmobText();
        }
    }

    @Override
    public void clickYes() {
        ConsentInformation.getInstance(this)
                .setConsentStatus(ConsentStatus.PERSONALIZED);
        closeRodoFragment();
        startAdmobSplash();
    }

    private void closeRodoFragment() {
        if (rodoFragment != null) {
            super.onBackPressed();
        }
        rodoFragment = null;
    }

    @Override
    public void clickNo() {
        ConsentInformation.getInstance(this)
                .setConsentStatus(ConsentStatus.NON_PERSONALIZED);
    }

    @Override
    public void clickAcceptPolicy() {
        closeRodoFragment();
        startAdmobSplash();
    }

    public abstract boolean isNoAdsPaymentAvailable();

    @Override
    public void onNoAdsPaymentProcessingFinished(boolean noAdsBought) {
        closeRodoFragment();
        Config.setNoAdsBought(noAdsBought);
        if (noAdsBought) {
            startActivity(getIntentToLaunch());
            finish();
        } else {
            showConsent();
        }
    }
}
