package pl.netigen.netigenapi;

import android.content.Intent;

import androidx.annotation.NonNull;


interface ISplashActivity {
    String[] PUBLISHER_IDS = {"pub-4699516034931013"};

    /**
     * @return Intent(Activity) to launch after initialization
     */
    Intent getIntentToLaunch();

    @NonNull
    ConfigBuilder getConfigBuilder();

    default String[] getPublisherIds() {
        return PUBLISHER_IDS;
    }

    int getContentView();

    int getSplashFragmentRodoContainerId();

    void onNoAdsPaymentProcessingFinished(boolean noAdsBought);

    default Boolean shouldShowInterstitialAd() {
        return true;
    }

}
