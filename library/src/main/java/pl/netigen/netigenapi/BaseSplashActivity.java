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


public abstract class BaseSplashActivity extends AppCompatActivity implements ISplashActivity, LoadProgressListener, AdmobIds, RodoFragment.ClickListener {
    public static final String PUB = "pub-4699516034931013";
    private static final String RODO_FRAGMENT_TAG = "rodo";
    private AdmobManager admobManager;
    private RodoFragment rodoFragment;
    private boolean canCommitFragment;
    private boolean isRodoConfirmed;
    private boolean isLoaded;
    private Handler initAdmobHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        boolean noAdsBought = getSharedPreferences(Const.PREFERENCES_NAME, MODE_PRIVATE).getBoolean(Const.NO_ADS, false);
        if (noAdsBought) {
            onRodoConfirmed();
        } else {
            final ConsentInformation consentInformation = ConsentInformation.getInstance(this);
            String[] publisherIds = {PUB};
            consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
                @Override
                public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                    boolean isInEea = ConsentInformation.getInstance(BaseSplashActivity.this).isRequestLocationInEeaOrUnknown();
                    ConstRodo.setIsInEea(isInEea);
                    if (isInEea && consentInformation.getConsentStatus() == ConsentStatus.UNKNOWN) {
                        initRodoFragment();
                    } else {
                        onRodoConfirmed();
                    }
                }

                @Override
                public void onFailedToUpdateConsentInfo(String errorDescription) {
                    onRodoConfirmed();
                }
            });
        }
    }

    private void onRodoConfirmed() {
        isRodoConfirmed = true;
        init();
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
                    .add(R.id.fragmentRodo, rodoFragment, RODO_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }

    private void init() {
        try {
            onInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (initAdmobHandler == null) {
            initAdmobHandler = new Handler();
            initAdmobHandler.post(this::initAdmob);
        }
    }

    private void initAdmob() {
        MobileAds.initialize(this, getAdmobAppID());
        admobManager = AdmobManager.create(getBannerId(), getFullScreenId(), this);
        admobManager.splashScreenOnCreate();
        if (!setUpLoaderClass() || isLoaded) {
            onFinishLoading();
        } else {
            isRodoConfirmed = true;
        }
    }

    protected abstract void onInit();

    @Override
    public void onLoadProgress(String progressText) {

    }

    @Override
    public void onBackPressed() {
        if (rodoFragment != null) {
            rodoFragment.showAdmobText();
        }
    }

    @Override
    public void onFinishLoading() {
        isLoaded = true;
        if (isRodoConfirmed) {
            if (admobManager == null) {
                if (initAdmobHandler == null) {
                    initAdmob();
                }
            } else {
                admobManager.waitOrShowFullScreen(getIntentToLaunch(), true);
            }
        }
    }

    @Override
    public void clickYes() {
        ConsentInformation.getInstance(this)
                .setConsentStatus(ConsentStatus.PERSONALIZED);
        closeRodoFragment();
        onRodoConfirmed();
    }

    private void closeRodoFragment() {
        super.onBackPressed();
    }

    @Override
    public void clickNo() {
        ConsentInformation.getInstance(this)
                .setConsentStatus(ConsentStatus.NON_PERSONALIZED);
    }

    @Override
    public void clickAcceptPolicy() {
        closeRodoFragment();
        onRodoConfirmed();
    }

    public abstract boolean isNoAdsPaymentAvailable();
}
