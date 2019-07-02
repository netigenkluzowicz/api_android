package pl.netigen.rewards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.netigen.netigenapi.R;

class RewardedItemsAdapter extends RecyclerView.Adapter<RewardedItemsAdapter.RewardViewHolder>{

    private RewardList rewardList;

    public RewardedItemsAdapter(RewardList rewardList) {
        this.rewardList = rewardList;
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView view = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reward, parent, false);
        return new RewardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return rewardList.size();
    }

    public class RewardViewHolder extends RecyclerView.ViewHolder {

        public RewardViewHolder(@NonNull ImageView itemView) {
            super(itemView);
        }

    }
}
