package pl.netigen.rewards;

import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.util.List;

class RewardParams {

    @StringRes
    public int buttonWatchAdResId = 0;
    @StringRes
    public int rewardDescrpitonTextResId = 0;
    @DrawableRes
    public int rewardedHeaderResId;
    @DrawableRes
    public int closeButtonResId;
    @DrawableRes
    public int watchAdButtonBackgroundResId;

    public List<RewardItem> rewards;
    public View background;
    public boolean isHigher;
    public double sizeX = 0.0f, sizeY = 0.0f;

    public RewardParams() {
    }

    public RewardParams(int buttonWatchAdResId, int rewardDescrpitonTextResId, List<RewardItem> rewards, View background,
                        int rewardedHeaderResId, int closeButtonResId, int watchAdButtonBackgroundResId, boolean isHigher,
                        double sizeX, double sizeY) {
        this.buttonWatchAdResId = buttonWatchAdResId;
        this.rewardDescrpitonTextResId = rewardDescrpitonTextResId;
        this.rewards = rewards;
        this.background = background;
        this.rewardedHeaderResId = rewardedHeaderResId;
        this.closeButtonResId = closeButtonResId;
        this.watchAdButtonBackgroundResId = watchAdButtonBackgroundResId;
        this.isHigher = isHigher;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }


}