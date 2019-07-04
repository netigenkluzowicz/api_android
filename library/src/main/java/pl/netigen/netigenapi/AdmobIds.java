package pl.netigen.netigenapi;

import pl.netigen.rewards.RewardsListener;

public interface AdmobIds {
    String getFullScreenId();

    String getBannerId();

    String getAdmobAppID();

    boolean isMultiFullScreenApp();

    default String getRewardedAdId() {
        return null;
    }

    default RewardsListener getRewardsListener() {
        return null;
    }

    default boolean shouldLoadRewardedAd() { return false; }
}
