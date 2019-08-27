package pl.netigen.netigenapi;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.android.gms.ads.MobileAds;

import pl.netigen.rodo.ConstRodo;
import pl.netigen.rodo.GDPRDialogFragment;

public abstract class BaseSplashActivity extends AppCompatActivity implements ISplashActivity, AdmobIds, GDPRDialogFragment.GDPRClickListener {

    AdmobManager admobManager;
    boolean consentFinished;

    private GDPRDialogFragment gdprDialogFragment;
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
                    consentFinished = true;
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
            gdprDialogFragment = GDPRDialogFragment.Companion.newInstance();
            gdprDialogFragment.setIsPayOptions(isNoAdsPaymentAvailable());
            gdprDialogFragment.show(getSupportFragmentManager().beginTransaction().addToBackStack(null), "");
        }
    }

    public abstract boolean showFullscreen();

    private void startAdmobSplash() {
        if (showFullscreen()) {
            if (initAdmobHandler == null) {
                initAdmobHandler = new Handler();
                initAdmobHandler.post(this::initAdmob);
            }
        } else {
            startActivity(getIntentToLaunch());
            finish();
        }
    }

    void initAdmob() {
        MobileAds.initialize(this, getAdmobAppID());
        admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        admobManager.splashScreenOnCreate(getIntentToLaunch());
    }

    @Override
    public void onBackPressed() {
        if (gdprDialogFragment != null) {
            gdprDialogFragment.showAdmobText();
        }
    }

    @Override
    public void clickYes() {
        ConsentInformation.getInstance(this)
                .setConsentStatus(ConsentStatus.PERSONALIZED);
        closeRodoFragment();
        startAdmobSplash();
        consentFinished = true;
    }

    private void closeRodoFragment() {
        if (gdprDialogFragment != null) {
            super.onBackPressed();
        }
        gdprDialogFragment = null;
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
        consentFinished = true;
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
