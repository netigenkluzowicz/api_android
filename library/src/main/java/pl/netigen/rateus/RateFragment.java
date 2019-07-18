package pl.netigen.rateus;


import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import pl.netigen.netigenapi.R;

public class RateFragment extends AppCompatDialogFragment {
    private RateUs rateUs;

    public RateFragment() {
    }

    public static RateFragment newInstance(RateUs rateUs) {
        RateFragment rateFragment = new RateFragment();
        rateFragment.rateUs = rateUs;
        return rateFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);

        View view;

        if(rateUs.isDefaultPopUp) {
            view = inflater.inflate(R.layout.netigen_api_rate_us_dialog, container, false);
            if (rateUs == null) {
                dismiss();
                return view;
                setDefaultPopupViews(view);
            }

        }
        else
        {      view = inflater.inflate(getResources().getLayout(rateUs.customLayoutId), container, false);
            if (rateUs == null) {
                dismiss();
                return view;
            }

        }
        TextView noTextView = view.findViewById(R.id.rateUsFragmentNoTextView);
        noTextView.setText(rateUs.negativeResId);
        noTextView.setOnClickListener(v -> {
            rateUs.clickNo();
        setNegativeButton(view);
        setPositiveButton(view);
        setNeutralButton(view);
    private void setDefaultPopupViews(View view) {
        ((TextView) view.findViewById(R.id.rateUsFragmentTitleTextView)).setText(rateUs.titleResId);
        ((TextView) view.findViewById(R.id.rateUsFragmentsAppNameTextView)).setText(rateUs.appNameResId);
        ((TextView) view.findViewById(R.id.rateUsFragmentAskForRateTextView)).setText(rateUs.askForRateUsInfoId);
        ((ImageView) view.findViewById(R.id.rateUsFragmentIconImageView)).setImageResource(rateUs.appIconResId);
    }

    private void setNeutralButton(View view) {
        TextView laterTextView = view.findViewById(R.id.rateUsFragmentLaterTextView);
        laterTextView.setText(rateUs.notAskAgainResId);
        laterTextView.setOnClickListener(v -> {
            rateUs.clickLater();
            dismiss();
        });
    }

    private void setPositiveButton(View view) {
        TextView yesTextView = view.findViewById(R.id.rateUsFragmentYesTextView);
        yesTextView.setText(rateUs.positiveResId);
        yesTextView.setOnClickListener(v -> {
            rateUs.clickYes();
            dismiss();
        });
    }

    private void setNegativeButton(View view) {
        TextView noTextView = view.findViewById(R.id.rateUsFragmentNoTextView);
        noTextView.setText(rateUs.negativeResId);
        noTextView.setOnClickListener(v -> {
            rateUs.clickNo();
            dismiss();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogSize(0.75, 0.85);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        rateUs = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setDialogSize(double heightMultiplier, double widthMultiplier) {
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
}
