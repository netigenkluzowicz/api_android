package pl.netigen.netigenapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.lang.annotation.Retention;
import java.util.List;

import pl.netigen.rewards.RewardItem;
import pl.netigen.rewards.RewardListenersList;
import pl.netigen.rewards.RewardsListener;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class AdmobManager implements RewardedVideoAdListener {

    private static final String TAG = "AdmobManager";

    @IntDef({RewardError.FAILED_TO_LOAD, RewardError.NOT_LOADED_YET, RewardError.FAILED_TO_REWARD})
    @Retention(SOURCE)
    public @interface RewardError {
        int FAILED_TO_LOAD = 0;
        int NOT_LOADED_YET = 1;
        int FAILED_TO_REWARD = 2;
    }

    private static final long REFRESH_TIME = 333;
    private static final long DEFAULT_MIN_WAIT = 2000;
    private static final long DEFAULT_MAX_WAIT = 7000;
    private static InterstitialAd interstitial;
    private static boolean fullAdError;
    private final Handler fullScreenHandler = new Handler();
    private String fullScreenId;
    private String bannerId;
    private Activity activity;
    private AdView bannerView;
    private long loadingAdsStartTime;
    private long minWaitForSplashFullScreen = DEFAULT_MIN_WAIT;
    private long maxWaitForSplashFullScreen = DEFAULT_MAX_WAIT;
    private int loadedBannerOrientation = 0;
    private boolean isMultiFullscreenApp;
    private boolean isSplashInBackground;
    private boolean noAdInstance;
    private boolean launched;
    private RewardedVideoAd rewardedVideoAd;
    private String lastLoadedRewardedAdId;
    private boolean isRewardedAdLoading = false;
    private List<RewardItem> rewardItems;
    private boolean wasRewardAdSuccessful = false;
    private RewardListenersList rewardsListeners;

    private AdmobManager(String bannerId, String fullScreenId, @NonNull Activity activity) {
        this.bannerId = bannerId;
        this.fullScreenId = fullScreenId;
        this.activity = activity;
        rewardsListeners = new RewardListenersList();
    }

    private AdmobManager() {

    }

    public static AdmobManager create(String bannerId, String fullScreenId, Activity activity) {
        return new AdmobManager(bannerId, fullScreenId, activity);
    }

    public static AdmobManager createNoAdsInstance() {
        AdmobManager admobManager = new AdmobManager();
        admobManager.noAdInstance = true;
        return admobManager;
    }

    void createRewardedVideo(RewardsListener rewardsListener) {
        if (rewardsListeners == null) {
            rewardsListeners = new RewardListenersList();
        }
        rewardsListeners.add(rewardsListener);

        MobileAds.initialize(activity);
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
        rewardedVideoAd.setRewardedVideoAdListener(this);
    }

    void loadRewardedVideo(String rewardedAdId) {
        lastLoadedRewardedAdId = rewardedAdId;
        if (!rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.loadAd(rewardedAdId, new AdRequest.Builder().build());
            isRewardedAdLoading = true;
        }
    }

    public void showRewardedVideoForItems(@NonNull List<pl.netigen.rewards.RewardItem> rewardItems, @Nullable List<RewardsListener> listeners) {
        if (listeners != null)
            rewardsListeners.addAll(listeners);
        if (Config.isNoAdsBought()) return;
        this.rewardItems = rewardItems;
        if (rewardedVideoAd != null && rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        } else {
            if (!isRewardedAdLoading) {
                loadRewardedVideo(lastLoadedRewardedAdId);
            }
            if (rewardsListeners != null) {
                for (RewardsListener listener : rewardsListeners) {
                    if (listener != null)
                        listener.onFail(RewardError.NOT_LOADED_YET);
                }
            }
        }
    }

    public long getMaxWaitForSplashFullScreen() {
        return maxWaitForSplashFullScreen;
    }

    public void setMaxWaitForSplashFullScreen(long maxWaitForSplashFullScreen) {
        this.maxWaitForSplashFullScreen = maxWaitForSplashFullScreen;
    }

    private void addView(RelativeLayout layout, AdView adView) {
        if (adView.getParent() != null) {
            ((ViewGroup) adView.getParent()).removeView(adView);
        }
        layout.addView(adView);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        adView.setLayoutParams(params);
        adView.requestLayout();
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private AdRequest getAdRequest() {
        AdRequest.Builder builder = new AdRequest.Builder();
        if (Config.isInDebugMode()) {
            builder.addTestDevice("F1F415DDE480395A4D21C26D6C6A9619")
                    .addTestDevice("9F65EEB1B6AED06CBE01CFEDA106BD29")
                    .addTestDevice("0F4B0296B48D2C6478D7E9A89DDD07F8")
                    .addTestDevice("593C1BC2C754805F5EFBCD8D4E288805")
                    .addTestDevice("E4347B3256669EAB2235222F475C8492")
                    .addTestDevice("0BFB00BFF8850BE0B8D40286BEDF317E")
                    .addTestDevice("59E58CCD5AB9F4ED41033F114E088239")
                    .addTestDevice("E42C3769BD763551CAC733B6AD662C0D")
                    .addTestDevice("14D51CBB5AB10BDC1FF61BAECA19C9AA")
                    .addTestDevice("8A575BCD3FC92B5719700193610FF48D")
                    .addTestDevice("8B1306F1E7DBD7B656E55F89DFC222D7")
                    .addTestDevice("3F520FF0CA7D49829C8E876C954D8E3D")
                    .addTestDevice("CFB9B2A279566BB6577918A8A8C8F849")
                    .addTestDevice("65364CA3C9CF87116F1D374660DF1235")
                    .addTestDevice("209772FAC421F8EC3FF0D98B7A72FAD2")
                    .addTestDevice("14D51CBB5AB10BDC1FF61BAECA19C9AA")
                    .addTestDevice("3D1FCDD4B6DC7E453846A04D214FD12D")
                    .addTestDevice("43AAFCE5A6B9E8FCDC58E58087AEC4EF")
                    .addTestDevice("AD2180512DE8B1EE611AB4645A69E470")
                    .addTestDevice("379BED7628AE4885B439939575F9F292")
                    .addTestDevice("15E1CF40903FB9938FFBFDBA8A9076E5");

            List<String> testDevices = Config.getTestDevices();
            if (testDevices != null) {
                for (int i = 0; i < testDevices.size(); i++) {
                    builder.addTestDevice(testDevices.get(i));
                }
            }
        }
        if (ConsentInformation.getInstance(activity).getConsentStatus() == ConsentStatus.NON_PERSONALIZED) {
            Bundle extras = new Bundle();
            extras.putString("npa", "1");
            return builder.addNetworkExtrasBundle(AdMobAdapter.class, extras)
                    .build();
        }

        return builder.build();
    }

    public void onBannerAdResume(RelativeLayout relativeLayout) {
        if (isNoAdsBought()) {
            return;
        }
        if (bannerView != null || loadedBannerOrientation != activity.getResources().getConfiguration().orientation) {
            loadBanner();
        }
        if (relativeLayout.getChildCount() == 0 || relativeLayout.getChildAt(0) != bannerView) {
            addView(relativeLayout, bannerView);
        }
        bannerView.resume();
    }

    private boolean isNoAdsBought() {
        return Config.isNoAdsBought() || noAdInstance;
    }

    public void onBannerAdPause() {
        if (isNoAdsBought()) {
            if (bannerView != null) {
                ViewGroup parent = (ViewGroup) bannerView.getParent();
                if (parent != null) parent.removeView(bannerView);
            }
            return;
        }
        if (bannerView != null) {
            bannerView.pause();
            ViewGroup parent = (ViewGroup) bannerView.getParent();
            if (parent != null) parent.removeView(bannerView);
        }
    }

    private void loadBanner() {
        if (isNoAdsBought()) {
            return;
        }
        if (bannerView == null || loadedBannerOrientation != activity.getResources().getConfiguration().orientation) {
            loadedBannerOrientation = activity.getResources().getConfiguration().orientation;
            bannerView = new AdView(activity);
            bannerView.setAdSize(AdSize.SMART_BANNER);
            bannerView.setAdUnitId(bannerId);
        }
        bannerView.loadAd(getAdRequest());
    }

    private void launchSplashLoaderOrStartMainActivity(Intent activityToLaunch) {
        loadingAdsStartTime = System.currentTimeMillis();
        fullScreenHandler.removeCallbacksAndMessages(null);
        if (isNoAdsBought()) {
            launchTargetActivity(activityToLaunch);
            return;
        }
        if (interstitial.isLoaded()) {
            showFullScreenIfPossible(success -> launchTargetActivity(activityToLaunch));
        } else if (!isOnline()) {
            launchTargetActivity(activityToLaunch);
        } else {
            SplashScreenLoader splashScreenLoader = new SplashScreenLoader(activityToLaunch);
            fullScreenHandler.postDelayed(splashScreenLoader, REFRESH_TIME);
        }
    }

    public void showFullScreenIfPossible(@NonNull ShowFullScreenListener showFullScreenListener) {
        if (isNoAdsBought()) {
            showFullScreenListener.onShowedOrNotLoaded(false);
            return;
        }
        if (interstitial == null) {
            loadInterstitial(activity);
            showFullScreenListener.onShowedOrNotLoaded(false);
            return;
        }
        if (interstitial.isLoaded()) {
            interstitial.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    showFullScreenListener.onShowedOrNotLoaded(true);
                    if (isMultiFullscreenApp) {
                        loadInterstitialIfNeeded(activity);
                    }
                }
            });
            interstitial.show();
        } else {
            showFullScreenListener.onShowedOrNotLoaded(false);
            if (!interstitial.isLoading()) {
                loadInterstitialIfNeeded(activity);
            }
        }
    }

    private void loadInterstitial(Context context) {
        if (context != null) {
            interstitial = new InterstitialAd(context);
            interstitial.setAdUnitId(fullScreenId);
            interstitial.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int errorCode) {
                    super.onAdFailedToLoad(errorCode);
                    fullAdError = true;
                }
            });
        }
    }

    void splashScreenOnCreate(Intent intentToLaunch) {
        loadInterstitialIfNeeded(activity);
        launchSplashLoaderOrStartMainActivity(intentToLaunch);
    }

    void loadInterstitialIfNeeded(Context context) {
        if (isNoAdsBought()) {
            return;
        }
        if (interstitial == null) {
            loadInterstitial(context);
        }
        fullAdError = false;
        if (interstitial != null && !interstitial.isLoading()) {
            interstitial.loadAd(getAdRequest());
        }
    }

    private void launchTargetActivity(Intent activityToLaunch) {
        fullScreenHandler.removeCallbacksAndMessages(null);
        if (!launched) {
            activity.startActivity(activityToLaunch);
            activity.finish();
            launched = true;
        }
    }

    public void setIsMultiFullscreenApp(boolean isMultiFullscreenApp) {
        this.isMultiFullscreenApp = isMultiFullscreenApp;
    }

    public void splashScreenOnStop() {
        isSplashInBackground = true;
    }

    public void splashScreenOnStart() {
        isSplashInBackground = false;
    }

    public void splashScreenOnDestroy() {
        fullScreenHandler.removeCallbacksAndMessages(null);
    }

    public void bannerActivityOnCreate() {
        if (fullAdError && isMultiFullscreenApp) {
            loadInterstitialIfNeeded(activity);
        }
    }

    public long getMinWaitForSplashFullScreen() {
        return minWaitForSplashFullScreen;
    }

    public void setMinWaitForSplashFullScreen(long minWaitForSplashFullScreen) {
        this.minWaitForSplashFullScreen = minWaitForSplashFullScreen;
    }

    public RewardedVideoAd getRewardedVideoAd() {
        return rewardedVideoAd;
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.i(TAG, "onRewardedVideoAdLoaded: ");
        isRewardedAdLoading = false;
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.i(TAG, "onRewardedVideoAdOpened: ");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.i(TAG, "onRewardedVideoStarted: ");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.i(TAG, "onRewardedVideoAdClosed: ");
        loadRewardedVideo(lastLoadedRewardedAdId);
    }

    @Override
    public void onRewarded(com.google.android.gms.ads.reward.RewardItem rewardItem) {
        Log.i(TAG, "onRewarded: rewardItem type" + rewardItem.getType() + " amount " + rewardItem.getAmount());
        try {
            if (rewardsListeners != null) {
                rewardsListeners.callOnSuccess(rewardItems);
            }
            wasRewardAdSuccessful = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (rewardsListeners != null) {
                rewardsListeners.callOnFail(RewardError.NOT_LOADED_YET);
            }
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.i(TAG, "onRewardedVideoAdLeftApplication: ");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.i(TAG, "onRewardedVideoAdFailedToLoad: i " + i);
        if (rewardsListeners != null) {
            rewardsListeners.callOnFail(RewardError.FAILED_TO_LOAD);
        }
        loadRewardedVideo(lastLoadedRewardedAdId);
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.i(TAG, "onRewardedVideoCompleted: ");
    }

    public interface ShowFullScreenListener {
        void onShowedOrNotLoaded(boolean success);
    }

    private class SplashScreenLoader implements Runnable {
        Intent activityToLaunch;

        SplashScreenLoader(Intent activityToLaunch) {
            this.activityToLaunch = activityToLaunch;
        }

        @Override
        public void run() {
            if (refreshHandler()) {
                fullScreenHandler.postDelayed(this, REFRESH_TIME);
            } else {
                if (isNoAdsBought()) {
                    launchTargetActivity(activityToLaunch);
                    return;
                }
                if (interstitial.isLoaded()) {
                    showFullScreenIfPossible(success -> launchTargetActivity(activityToLaunch));
                    fullScreenHandler.removeCallbacksAndMessages(null);
                } else if (System.currentTimeMillis() - loadingAdsStartTime < maxWaitForSplashFullScreen) {
                    if (fullAdError) {
                        if (isOnline()) {
                            fullScreenHandler.postDelayed(this, REFRESH_TIME);
                        } else {
                            launchTargetActivity(activityToLaunch);
                        }
                    } else {
                        fullScreenHandler.postDelayed(this, REFRESH_TIME);
                    }
                } else {
                    launchTargetActivity(activityToLaunch);
                }
            }
        }

        private boolean refreshHandler() {
            return System.currentTimeMillis() - loadingAdsStartTime < minWaitForSplashFullScreen || isSplashInBackground;
        }
    }
}