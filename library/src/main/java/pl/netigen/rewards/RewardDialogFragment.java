package pl.netigen.rewards;

import android.app.Activity;
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
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;

import java.util.List;

import pl.netigen.netigenapi.BaseBannerActivity;
import pl.netigen.netigenapi.R;

public class RewardDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = "RewardDialogFragment";

    private ImageView imageViewRewardedHeader;
    private ImageView imageViewWatchButton;
    private ImageView imageViewCloseButton;

    private LinearLayout linearLayoutTop;
    private LinearLayout linearLayoutBottom;
    private LinearLayout linearLayoutContainer;

    private AppCompatTextView textViewButtonPossitive;
    private AppCompatTextView textViewRewardDescription;

    private View backgroundView;
    private GridLayoutManager recyclerViewGridLayoutManager;

    private RewardParams rewardParams;
    private BaseBannerActivity baseBannerActivity;
    private Activity Activity;

    private static RewardDialogFragment newInstance(RewardParams rewardParams, BaseBannerActivity baseBannerActivity) {
        RewardDialogFragment rewardDialogFragment = new RewardDialogFragment();
        rewardDialogFragment.rewardParams = rewardParams;
        rewardDialogFragment.baseBannerActivity = baseBannerActivity;
        return rewardDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setDialog();
        View view = inflater.inflate(R.layout.dialog_fragment_reward, container, false);

        setDialogSize(rewardParams.sizeY, rewardParams.sizeX);

        if (rewardParams == null || getActivity() == null) {
            dismiss();
            return view;
        }

        textViewButtonPossitive = view.findViewById(R.id.textViewPositiveButtonText);
        if(rewardParams.buttonWatchAdResId!=0){
            textViewButtonPossitive.setText(rewardParams.buttonWatchAdResId);
        }

        textViewRewardDescription = view.findViewById(R.id.textViewRewardDescription);
        if(rewardParams.rewardDescrpitonTextResId !=0){
            textViewRewardDescription.setText(rewardParams.rewardDescrpitonTextResId);
        }

        imageViewRewardedHeader = view.findViewById(R.id.imageViewRewardedHeader);
        if (rewardParams.rewardedHeaderResId != 0) {
            Glide.with(view).load(rewardParams.rewardedHeaderResId).into(imageViewRewardedHeader);
        }

        imageViewWatchButton = view.findViewById(R.id.buttonPositive);
        if (rewardParams.watchAdButtonBackgroundResId != 0) {
            Glide.with(view).load(rewardParams.watchAdButtonBackgroundResId).into(imageViewWatchButton);
        }
        imageViewWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseBannerActivity.getAdmobManager().showRewardedVideoForItems(rewardParams.rewards);
                dismiss();
            }
        });

        imageViewCloseButton = view.findViewById(R.id.imageViewClose);
        if (rewardParams.closeButtonResId != 0) {
            Glide.with(view).load(rewardParams.closeButtonResId).into(imageViewCloseButton);
        }

        linearLayoutContainer = view.findViewById(R.id.linearLayoutContainer);

        linearLayoutTop = view.findViewById(R.id.linearLayoutTop);

        linearLayoutBottom = view.findViewById(R.id.linearLayoutBottom);
        ImageView imageView = null;
        for (int i = 0; i < rewardParams.rewards.size(); i++) {
            imageView = new ImageView(view.getContext());
            if (i % 2 == 0) {
                linearLayoutTop.addView(imageView);
            } else {
                linearLayoutBottom.addView(imageView);
            }
            ((LinearLayout.LayoutParams) imageView.getLayoutParams()).leftMargin = (int) getResources().getDimension(R.dimen.defaultRewardItemsPadding);
            ((LinearLayout.LayoutParams) imageView.getLayoutParams()).rightMargin = (int) getResources().getDimension(R.dimen.defaultRewardItemsPadding);
            imageView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.rewardItemDefaultSize);
            imageView.getLayoutParams().width = (int) getResources().getDimension(R.dimen.rewardItemDefaultSize);
            Glide.with(view).load(rewardParams.rewards.get(i).getPath()).into(imageView);
        }
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
        setDialogSize(0, 0.75);
    }

    public static class Builder {

        private BaseBannerActivity baseBannerActivity;
        private RewardParams rewardParams;

        public Builder(BaseBannerActivity baseBannerActivity) {
            this.baseBannerActivity = baseBannerActivity;
            this.rewardParams = new RewardParams();
        }

        public Builder setButtonWatchAdResId(int buttonWatchAdResId) {
            this.rewardParams.buttonWatchAdResId = buttonWatchAdResId;
            return this;
        }

        public Builder setContentDescriptionResId(int contentDescriptionResId) {
            this.rewardParams.rewardDescrpitonTextResId = contentDescriptionResId;
            return this;
        }

        public Builder setRewards(List<RewardItem> rewards) {
            this.rewardParams.rewards = rewards;
            return this;
        }

        public Builder setBackground(View background) {
            this.rewardParams.background = background;
            return this;
        }

        public Builder setRewardedHeader(int rewardedHeaderResId) {
            this.rewardParams.rewardedHeaderResId = rewardedHeaderResId;
            return this;
        }

        public Builder setCloseButton(int closeButtonResId) {
            this.rewardParams.closeButtonResId = closeButtonResId;
            return this;
        }

        public Builder setWatchAdButtonBackground(int watchAdButtonBackgroundResId) {
            this.rewardParams.watchAdButtonBackgroundResId = watchAdButtonBackgroundResId;
            return this;
        }

        public Builder setIsHigher(boolean isHigher) {
            this.rewardParams.isHigher = isHigher;
            return this;
        }

        public Builder setSizeX(double sizeX) {
            this.rewardParams.sizeX = sizeX;
            return this;
        }

        public Builder setSizeY(double sizeY) {
            this.rewardParams.sizeY = sizeY;
            return this;
        }

        public Builder setSize(double sizeX, double sizeY) {
            this.rewardParams.sizeY = sizeY;
            this.rewardParams.sizeX = sizeX;
            return this;
        }

        public RewardDialogFragment create(BaseBannerActivity baseBannerActivity) {
            return RewardDialogFragment.newInstance(rewardParams, baseBannerActivity);
        }

        public void show() {
            create(baseBannerActivity).show(baseBannerActivity.getSupportFragmentManager(), TAG);
        }
    }
}
