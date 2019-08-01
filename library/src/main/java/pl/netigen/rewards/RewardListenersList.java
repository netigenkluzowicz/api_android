package pl.netigen.rewards;

import java.util.ArrayList;
import java.util.List;

public class RewardListenersList extends ArrayList<RewardsListener> {

    public void callOnFail(int rewardError) {
        for (RewardsListener listener : this) {
            if (listener != null)
                listener.onFail(rewardError);
        }
    }

    public void callOnSuccess(List<RewardItem> rewardedItems) {
        for (RewardsListener listener : this) {
            if (listener != null)
                listener.onSuccess(rewardedItems);
        }
    }

    @Override
    public boolean add(RewardsListener rewardsListener) {
        if (!this.contains(rewardsListener))
            return super.add(rewardsListener);
        else return false;
    }
}
