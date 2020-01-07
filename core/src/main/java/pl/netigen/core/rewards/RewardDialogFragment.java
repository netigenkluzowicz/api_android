package pl.netigen.core.rewards;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.List;

import pl.netigen.core.R;
import pl.netigen.core.netigenapi.NetigenMainActivity;

public class RewardDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = "RewardDialogFragment";

    public static double MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    public static double WRAP_CONTENT = 0.0;

    private ImageView imageViewRewardedHeader;
    private ImageView imageViewCloseButton;
    private ImageView imageViewButtonPositive;
    private TextView textViewRewardDescription;
    private AppCompatTextView textViewPositiveButton;
    private LinearLayout linearLayoutTop;
    private LinearLayout linearLayoutBottom;
    private LinearLayout linearLayoutContainer;
    private FrameLayout backgroundView;

    private RewardParams rewardParams;
    private NetigenMainActivity netigenMainActivity;

    private static RewardDialogFragment newInstance(RewardParams rewardParams, NetigenMainActivity netigenMainActivity) {
        RewardDialogFragment rewardDialogFragment = new RewardDialogFragment();
        rewardDialogFragment.rewardParams = rewardParams;
        rewardDialogFragment.netigenMainActivity = netigenMainActivity;
        return rewardDialogFragment;
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

        setLayoutViews(view);

        setDescriptionText();
        setHeader(view);
        setPositiveButton();
        setCloseButton(view);
        setBackground(view);
        setRewardedContent(view);
        setLayoutMargins();

        return view;
    }

    private void setLayoutViews(View view) {
        textViewRewardDescription = view.findViewById(R.id.textViewRewardDescription);
        imageViewRewardedHeader = view.findViewById(R.id.imageViewRewardedHeader);
        textViewPositiveButton = view.findViewById(R.id.textViewButtonPositive);
        imageViewCloseButton = view.findViewById(R.id.imageViewClose);
        linearLayoutContainer = view.findViewById(R.id.linearLayoutContainer);
        linearLayoutTop = view.findViewById(R.id.linearLayoutTop);
        linearLayoutBottom = view.findViewById(R.id.linearLayoutBottom);
        backgroundView = view.findViewById(R.id.backgroundView);
    }

    private void setHeader(View view) {
        if (rewardParams.rewardedHeaderResId != 0) {
            Glide.with(view).load(rewardParams.rewardedHeaderResId).into(imageViewRewardedHeader);
        }
    }

    private void setDescriptionText() {
        if (rewardParams.rewardDescriptionTextResId != 0) {
            textViewRewardDescription.setText(rewardParams.rewardDescriptionTextResId);
        } else {
            textViewRewardDescription.setVisibility(View.GONE);
            return;
        }

        if (rewardParams.descriptionTextColor != null) {
            textViewRewardDescription.setTextColor(rewardParams.descriptionTextColor);
        }

        if (rewardParams.textSizeDimenRes != 0) {
            textViewRewardDescription.setTextSize(getResources().getDimension(rewardParams.textSizeDimenRes) /
                    getResources().getDisplayMetrics().scaledDensity);
        }
    }

    private void setPositiveButton() {
        if (rewardParams.buttonPositiveTextResId != 0) {
            textViewPositiveButton.setText(rewardParams.buttonPositiveTextResId);
        } else {
            textViewPositiveButton.setVisibility(View.GONE);
        }

        if (rewardParams.buttonPositiveTextColor != null) {
            textViewPositiveButton.setTextColor(rewardParams.buttonPositiveTextColor);
        }

        if (rewardParams.buttonPositiveBackgroundDrawableId != 0) {
            textViewPositiveButton.setBackground(ContextCompat.getDrawable(getActivity(), rewardParams.buttonPositiveBackgroundDrawableId));
        }

        textViewPositiveButton.setOnClickListener(v -> {
            //todo  netigenMainActivity.getAdmobAds().showRewardedVideoForItems(rewardParams.rewards, rewardParams.listeners);
            dismiss();
        });
    }

    private void setCloseButton(View view) {
        if (rewardParams.closeButtonResId != 0) {
            Glide.with(view).load(rewardParams.closeButtonResId).into(imageViewCloseButton);
            imageViewCloseButton.setOnClickListener(v -> dismiss());
        }
    }

    private void setBackground(View view) {
        if (rewardParams.background != null) {
            backgroundView.addView(rewardParams.background);
        } else {
            ImageView imageViewBackground = new ImageView(getActivity());
            Glide.with(view).load(R.drawable.background_language_dialog).into(imageViewBackground);
            backgroundView.addView(imageViewBackground);
        }
    }

    private void setRewardedContent(View view) {
        ImageView imageView;
        for (int i = 0; i < rewardParams.rewards.size(); i++) {
            imageView = new ImageView(view.getContext());
            if (i % 2 == 0) {
                linearLayoutTop.addView(imageView);
            } else {
                linearLayoutBottom.addView(imageView);
            }
            ((LinearLayout.LayoutParams) imageView.getLayoutParams()).leftMargin = (int) getResources().getDimension(R.dimen.default_rewarded_line_padding);
            ((LinearLayout.LayoutParams) imageView.getLayoutParams()).rightMargin = (int) getResources().getDimension(R.dimen.default_rewarded_line_padding);
            imageView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.rewardItemDefaultSize);
            imageView.getLayoutParams().width = (int) getResources().getDimension(R.dimen.rewardItemDefaultSize);
            Glide.with(view).load(rewardParams.rewards.get(i).getPath()).into(imageView);
        }
    }

    private void setLayoutMargins() {
        if (rewardParams.marginsParamsHeader != null) {
            rewardParams.marginsParamsHeader.setNewLayoutParamsForView(imageViewRewardedHeader);
        }

        if (rewardParams.marginsParamsPositiveButton != null) {
            rewardParams.marginsParamsPositiveButton.setNewLayoutParamsForView(imageViewRewardedHeader);
        }

        if (rewardParams.marginsParamsDescription != null) {
            rewardParams.marginsParamsDescription.setNewLayoutParamsForView(imageViewRewardedHeader);
        }

        if (rewardParams.marginsParamsRewardsContainer != null) {
            rewardParams.marginsParamsRewardsContainer.setNewLayoutParamsForView(imageViewRewardedHeader);
        }
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
        Log.d(TAG, "setDialogSize: heightMultiplier " + heightMultiplier + " widthMultiplier " + widthMultiplier);
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
        setDialogSize(rewardParams.heightMultiplier, rewardParams.widthMultiplier);
    }

    public static class Builder {

        private NetigenMainActivity netigenMainActivity;
        private RewardParams rewardParams;

        public Builder(NetigenMainActivity netigenMainActivity) {
            this.netigenMainActivity = netigenMainActivity;
            this.rewardParams = new RewardParams();
        }

        public Builder setDescriptionTextStringId(int descriptionTextStringId) {
            this.rewardParams.rewardDescriptionTextResId = descriptionTextStringId;
            return this;
        }

        public Builder setDescriptionTextColor(Integer descriptionTextColor) {
            this.rewardParams.descriptionTextColor = descriptionTextColor;
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

        public Builder setRewardedHeader(int rewardedHeaderDrawableId) {
            this.rewardParams.rewardedHeaderResId = rewardedHeaderDrawableId;
            return this;
        }

        public Builder setCloseButton(int closeButtonDrawableId) {
            this.rewardParams.closeButtonResId = closeButtonDrawableId;
            return this;
        }

        public Builder setButtonPositiveBackground(int buttonPositiveBackgroundDrawableId) {
            this.rewardParams.buttonPositiveBackgroundDrawableId = buttonPositiveBackgroundDrawableId;
            return this;
        }

        public Builder setButtonPositiveTextColor(Integer buttonPositiveTextColor) {
            this.rewardParams.buttonPositiveTextColor = buttonPositiveTextColor;
            return this;
        }

        public Builder setButtonPositiveText(int buttonPositiveTextResId) {
            this.rewardParams.buttonPositiveTextResId = buttonPositiveTextResId;
            return this;
        }

        public Builder setWidthMultiplier(double widthMultiplier) {
            this.rewardParams.widthMultiplier = widthMultiplier;
            return this;
        }

        public Builder setHeightMultiplier(double heightMultiplier) {
            this.rewardParams.heightMultiplier = heightMultiplier;
            return this;
        }

        public Builder setSize(double widthMultiplier, double heightMultiplier) {
            this.rewardParams.heightMultiplier = heightMultiplier;
            this.rewardParams.widthMultiplier = widthMultiplier;
            return this;
        }

        public Builder setTopMarginForHeader(Integer headerTopMargin) {
            this.rewardParams.headerTopMargin = headerTopMargin;
            return this;
        }

        public Builder setDescriptionMargins(LayoutMargins layoutMargins) {
            this.rewardParams.marginsParamsDescription = layoutMargins;
            return this;
        }

        public Builder setRewardContainerMargins(LayoutMargins layoutMargins) {
            this.rewardParams.marginsParamsRewardsContainer = layoutMargins;
            return this;
        }

        public Builder setPositiveButtonMargins(LayoutMargins layoutMargins) {
            this.rewardParams.marginsParamsPositiveButton = layoutMargins;
            return this;
        }

        public Builder setTextSize(int textSizeDimenResId) {
            rewardParams.textSizeDimenRes = textSizeDimenResId;
            return this;
        }

        public Builder setHeaderMargins(LayoutMargins layoutMargins) {
            this.rewardParams.marginsParamsHeader = layoutMargins;
            return this;
        }

        public Builder addListener(RewardsListener listener) {
            this.rewardParams.listeners.add(listener);
            return this;
        }

        public RewardDialogFragment create(NetigenMainActivity netigenMainActivity) {
            return RewardDialogFragment.newInstance(rewardParams, netigenMainActivity);
        }

        public void show() {
            create(netigenMainActivity).show(netigenMainActivity.getSupportFragmentManager(), TAG);
        }
    }
}
