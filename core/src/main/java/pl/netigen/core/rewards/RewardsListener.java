package pl.netigen.core.rewards;

import java.util.List;

public interface RewardsListener {
    void onSuccess(List<RewardItem> rewardedItems);

    void onFail(int rewardError);
}
