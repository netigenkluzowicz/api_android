package pl.netigen.rewards;

import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.util.List;

class RewardParams {

    @StringRes
    int buttonPositiveTextResId = 0;
    @StringRes
    int rewardDescriptionTextResId = 0;
    @DrawableRes
    int rewardedHeaderResId;
    @DrawableRes
    int closeButtonResId;
    @DrawableRes
    int buttonPositiveBackgroundDrawableId;
    @DimenRes
    int textSizeDimenRes;

    public List<RewardItem> rewards;
    public View background;
    double widthMultiplier = 0.0, heightMultiplier = 0.0;
    Integer descriptionTextColor;
    Integer buttonPositiveTextColor;
    Integer headerTopMargin;
    LayoutMargins marginsParamsHeader = null;
    LayoutMargins marginsParamsDescription = null;
    LayoutMargins marginsParamsRewardsContainer = null;
    LayoutMargins marginsParamsPositiveButton = null;
    RewardListenersList listeners;

    RewardParams() {
        listeners = new RewardListenersList();
    }

}