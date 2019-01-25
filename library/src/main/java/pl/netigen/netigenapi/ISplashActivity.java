package pl.netigen.netigenapi;

import android.content.Intent;
import android.support.annotation.NonNull;


interface ISplashActivity {
    String[] PUBLISHER_IDS = {"pub-4699516034931013"};

    /**
     * Set up LoadProgressListener when loading additional resources is needed or return false otherwise
     * for example BaseResourcesLoader loader = new BaseResourcesLoader(this);
     *
     * @return true - when splash activity must wait for resources load finished or false otherwise
     */
    //
    boolean setUpLoaderClass();

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

}
