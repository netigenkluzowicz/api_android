package pl.netigen.language.info;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import pl.netigen.netigenapi.R;

public class TranslationInfoDialogFragment extends AppCompatDialogFragment {

    private DialogClickListener dialogClickListener;
    private Button buttonChangeLanguageOk;
    private Button buttonChangeLanguageDismiss;
    private TranslationInfoParams translationInfoParams;
    private TextView textViewTranslationInfoTitle;
    private TextView textViewTranslationInfoContent2;
    private TextView textViewTranslationInfoContent1;

    public TranslationInfoDialogFragment() {

    }

    public interface DialogClickListener {
        void onNegativeButtonClicked();

        void onPositiveButtonClicked();
    }

    private static final String TAG = "TranslationInfoDialogFr";

    public static TranslationInfoDialogFragment newInstance(TranslationInfoParams translationInfoParams) {
        TranslationInfoDialogFragment fragment = new TranslationInfoDialogFragment();
        fragment.translationInfoParams = translationInfoParams;
        Log.d(TAG, "newInstance: translationInfoParams.dialogClickListener null: " + (null == translationInfoParams.dialogClickListener));
        fragment.dialogClickListener = translationInfoParams.dialogClickListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View view = inflater.inflate(R.layout.dialog_fragment_translation_info, container, false);

        if (translationInfoParams == null || dialogClickListener == null) {
            dismiss();
            return view;
        }

        setTranslationInfoContent1(view);

        setTranslationInfoContent2(view);

        setTranslationInfoTitle(view);

        setPositiveButton(view);

        setNegativeButton(view);
        return view;
    }

    private void setTranslationInfoContent1(View view) {
        textViewTranslationInfoContent1 = view.findViewById(R.id.textView_translation_info_content1);
        textViewTranslationInfoContent1.setText(translationInfoParams.textContentTopResId);
    }

    private void setTranslationInfoContent2(View view) {
        textViewTranslationInfoContent2 = view.findViewById(R.id.textView_translation_info_content2);
        textViewTranslationInfoContent2.setText(translationInfoParams.textContentBottomResId);
    }

    private void setTranslationInfoTitle(View view) {
        textViewTranslationInfoTitle = view.findViewById(R.id.textView_translation_info_title);
        textViewTranslationInfoTitle.setText(translationInfoParams.titleResId);
    }


    public void setPositiveButton(View view) {
        buttonChangeLanguageOk = view.findViewById(R.id.button_positive);
        buttonChangeLanguageOk.setText(translationInfoParams.positiveButtonResId);
        buttonChangeLanguageOk.setOnClickListener(v -> {
            if (dialogClickListener != null) {
                dialogClickListener.onPositiveButtonClicked();
            }
            TranslationInfoDialogFragment.this.dismiss();
        });
    }

    private void setNegativeButton(View view) {
        buttonChangeLanguageDismiss = view.findViewById(R.id.button_negative);
        buttonChangeLanguageDismiss.setText(translationInfoParams.negativeButtonResId);

        buttonChangeLanguageDismiss.setOnClickListener(v -> {
            if (dialogClickListener != null) {
                dialogClickListener.onNegativeButtonClicked();
            }
            TranslationInfoDialogFragment.this.dismiss();
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogSize(0, 0.85);
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
            } else {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, (int) (maxHeight * heightMultiplier));
            }
            window.setGravity(Gravity.CENTER);
        }
    }

    public static class Builder {

        private TranslationInfoParams translationInfoParams;
        private AppCompatActivity appCompatActivity;

        public Builder(AppCompatActivity appCompatActivity) {
            this.appCompatActivity = appCompatActivity;
            this.translationInfoParams = new TranslationInfoParams();
        }

        public AppCompatActivity getActivity(){
            return appCompatActivity;
        }

        public Builder setDialogClickListener(DialogClickListener dialogClickListener) {
            this.translationInfoParams.dialogClickListener = dialogClickListener;
            return this;
        }

        public Builder setTitleResId(int titleResId) {
            translationInfoParams.titleResId = titleResId;
            return this;
        }

        public Builder setNegativeResId(int negativeResId) {
            translationInfoParams.negativeButtonResId = negativeResId;
            return this;
        }

        public Builder setPositiveResId(int positiveResId) {
            translationInfoParams.positiveButtonResId = positiveResId;
            return this;
        }

        public Builder setTopText(int textContent1) {
            translationInfoParams.textContentTopResId = textContent1;
            return this;
        }

        public Builder setBottomText(int textContent2) {
            translationInfoParams.textContentBottomResId = textContent2;
            return this;
        }

        public Builder setProperTranslations(String[] properTranslations){
            translationInfoParams.properTranslations = properTranslations;
            return this;
        }

        public TranslationInfoDialogFragment create() {
            return TranslationInfoDialogFragment.newInstance(translationInfoParams);
        }

        public void show() {
            create().show(appCompatActivity.getSupportFragmentManager(), "translation_info");
        }
    }

    private static class TranslationInfoParams {

        @StringRes
        public int titleResId = R.string.translation_information_title;
        @StringRes
        public int negativeButtonResId = R.string.cancel_upper_case;
        @StringRes
        public int positiveButtonResId = R.string.settings_upper_case;
        @StringRes
        public int textContentTopResId = R.string.translation_information_content1;
        @StringRes
        public int textContentBottomResId = R.string.translation_information_content2;

        public String[] properTranslations;

        public TranslationInfoDialogFragment.DialogClickListener dialogClickListener;

        public TranslationInfoParams(int titleResId, int negativeButtonResId,
                                     int positiveButtonResId, int textContentTopResId,
                                     int textContentBottomResId,
                                     @NonNull TranslationInfoDialogFragment.DialogClickListener dialogClickListener,
                                     String[] properTranslations) {
            if (titleResId != 0) this.titleResId = titleResId;
            if (negativeButtonResId != 0) this.negativeButtonResId = negativeButtonResId;
            if (positiveButtonResId != 0) this.positiveButtonResId = positiveButtonResId;
            if (textContentTopResId != 0) this.textContentTopResId = textContentTopResId;
            if (textContentBottomResId != 0) this.textContentBottomResId = textContentBottomResId;
            this.dialogClickListener = dialogClickListener;
            this.properTranslations = properTranslations;
        }

        public TranslationInfoParams() {

        }
    }
}
