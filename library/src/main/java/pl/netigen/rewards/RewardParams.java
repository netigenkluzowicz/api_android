package pl.netigen.rewards;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.netigen.netigenapi.R;

class RewardParams {

    @StringRes
    private int buttonWatchAdResId = (R.string.watch_ad);
    @StringRes
    private int contentDescriptionResId = (R.string.get_new_stickers_and_emoticons);

    private RewardList rewards;
    private View background;
    private Drawable rewardedHeader;
    private Drawable closeButton;
    private Drawable watchAdButtonBackground;
    private boolean isHigher;

}