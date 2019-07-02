package pl.netigen.rewards;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.netigen.netigenapi.R;

public class RewardedAdDialogFragment extends AppCompatDialogFragment {

    private RewardList rewardItems;

    private ImageView imageViewRewardedHeader;
    private ImageView imageViewWatchButton;
    private ImageView imageViewCloseButton;
    private RecyclerView recyclerViewRewardedItems;
    private RewardedItemsAdapter rewardedItemsAdapter;
    private View backgroundView;
    private GridLayoutManager recyclerViewGridLayoutManager;

    private RewardParams rewardParams;

    private static RewardedAdDialogFragment newInstance(RewardParams rewardParams){
        RewardedAdDialogFragment rewardedAdDialogFragment = new RewardedAdDialogFragment();
        rewardedAdDialogFragment.rewardParams = rewardParams;
        return rewardedAdDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setDialog();
        View view = inflater.inflate(R.layout.dialog_fragment_reward, container, false);

        if (rewardParams == null || getActivity() == null) {
            dismiss();
            return view;
        }

        imageViewRewardedHeader = view.findViewById(R.id.imageViewRewardedHeader);
        imageViewWatchButton = view.findViewById(R.id.imageViewButtonWatchAd);
        imageViewCloseButton = view.findViewById(R.id.imageViewClose);
        backgroundView = view.findViewById(R.id.backgroundView);
        recyclerViewRewardedItems = view.findViewById(R.id.recyclerViewRewardedItems);
        rewardedItemsAdapter = new RewardedItemsAdapter(rewardItems);
        recyclerViewGridLayoutManager = new GridLayoutManager(getActivity(), 6);
        recyclerViewGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position<3) {
                    return 3;
                } else {
                    return 2;
                }
            }
        });
        recyclerViewRewardedItems.setAdapter(rewardedItemsAdapter);
        recyclerViewRewardedItems.setLayoutManager(recyclerViewGridLayoutManager);

        return view;
    }

    private void setDialog() {
        Window window = getDialog().getWindow();

        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setDialogSize(double heightMultiplier, double widthMultiplier) {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display;
        if (window != null) {
            display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            int maxWidth = size.x;
            int maxHeight = size.y;
            if (heightMultiplier == 0.0 && widthMultiplier == 0.0) {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else if (widthMultiplier != 0.0 && heightMultiplier != 0.0) {
                window.setLayout((int) (maxWidth * widthMultiplier), (int) (maxHeight * heightMultiplier));
            } else if (widthMultiplier != 0.0) {
                window.setLayout((int) (maxWidth * widthMultiplier), ViewGroup.LayoutParams.WRAP_CONTENT);
            } else if (heightMultiplier != 0.0) {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, (int) (maxHeight * heightMultiplier));
            }
            window.setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogSize(0.0, 0.0);
    }
}
