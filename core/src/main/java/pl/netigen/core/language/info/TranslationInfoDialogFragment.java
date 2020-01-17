package pl.netigen.core.language.info;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import pl.netigen.core.R;
import pl.netigen.extensions.DialogFragmentExtensionKt;

import static pl.netigen.core.utils.Const.MARGIN_TOP;
import static pl.netigen.core.utils.Const.SCREEN_HEIGHT_IN_DP;

public class TranslationInfoDialogFragment extends AppCompatDialogFragment {

    private DialogClickListener dialogClickListener;
    private TranslationInfoParams translationInfoParams;

    public TranslationInfoDialogFragment() {

    }

    public interface DialogClickListener {
        void onNegativeButtonClicked();

        void onPositiveButtonClicked();
    }


    public static TranslationInfoDialogFragment newInstance(TranslationInfoParams translationInfoParams) {
        TranslationInfoDialogFragment fragment = new TranslationInfoDialogFragment();
        fragment.translationInfoParams = translationInfoParams;
        fragment.dialogClickListener = translationInfoParams.dialogClickListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_translation_info, container, false);
        setupDialog();

        if (dialogClickListener == null) {
            dismiss();
            return view;
        }

        return view;
    }

    private void setupDialog() {
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

        setTranslationInfoContent1(view);
        setTranslationInfoContent2(view);
        setTranslationInfoTitle(view);
        setPositiveButton(view);
        setNegativeButton(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        manageDialogSize();
    }

    private void manageDialogSize() {
        Context context = getContext();
        if (context != null) {
            Resources resources = getContext().getResources();
            if (resources != null) {
                manageDialogOrientation(resources.getConfiguration().orientation);
                if (getDeviceHeight(resources) < SCREEN_HEIGHT_IN_DP)
                    manageSmallScreenHeight();
            }
        }
    }

    private void manageDialogOrientation(int configuration) {
        if (configuration == Configuration.ORIENTATION_LANDSCAPE)
            DialogFragmentExtensionKt.setDialogSize(this, 410, 280);
        else
            DialogFragmentExtensionKt.setDialogSize(this, 280, 310);
    }

    private float getDeviceHeight(Resources resources) {
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels / displayMetrics.density;
    }

    private void manageSmallScreenHeight() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.y = MARGIN_TOP;
                window.setAttributes(params);
                window.setGravity(Gravity.TOP | Gravity.CENTER);
                DialogFragmentExtensionKt.setDialogSize(this, 415, 270);
            }
        }
    }

    private void setTranslationInfoContent1(View view) {
        TextView textViewTranslationInfoContent1 = view.findViewById(R.id.textView_translation_info_content1);
        textViewTranslationInfoContent1.setText(translationInfoParams.textContentTopResId);
    }

    private void setTranslationInfoContent2(View view) {
        TextView textViewTranslationInfoContent2 = view.findViewById(R.id.textView_translation_info_content2);
        textViewTranslationInfoContent2.setText(translationInfoParams.textContentBottomResId);
    }

    private void setTranslationInfoTitle(View view) {
        TextView textViewTranslationInfoTitle = view.findViewById(R.id.textView_translation_info_title);
        textViewTranslationInfoTitle.setText(translationInfoParams.titleResId);
    }


    public void setPositiveButton(View view) {
        TextView buttonChangeLanguageOk = view.findViewById(R.id.button_positive);
        buttonChangeLanguageOk.setText(translationInfoParams.positiveButtonResId);
        buttonChangeLanguageOk.setOnClickListener(v -> {
            if (dialogClickListener != null) {
                dialogClickListener.onPositiveButtonClicked();
            }
            dismiss();
        });
    }

    private void setNegativeButton(View view) {
        TextView buttonChangeLanguageDismiss = view.findViewById(R.id.button_negative);
        buttonChangeLanguageDismiss.setText(translationInfoParams.negativeButtonResId);

        buttonChangeLanguageDismiss.setOnClickListener(v -> {
            if (dialogClickListener != null) {
                dialogClickListener.onNegativeButtonClicked();
            }
            dismiss();
        });

    }

    public static class Builder {

        private TranslationInfoParams translationInfoParams;
        private AppCompatActivity appCompatActivity;

        public Builder(AppCompatActivity appCompatActivity) {
            this.appCompatActivity = appCompatActivity;
            this.translationInfoParams = new TranslationInfoParams();
        }

        public AppCompatActivity getActivity() {
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

        public Builder setProperTranslations(String[] properTranslations) {
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
        public int titleResId = R.string.translation_information_title_netigen;
        @StringRes
        public int negativeButtonResId = R.string.cancel_netigen;
        @StringRes
        public int positiveButtonResId = R.string.settings_normal_netigen;
        @StringRes
        public int textContentTopResId = R.string.translation_information_content1_netigen;
        @StringRes
        public int textContentBottomResId = R.string.translation_information_content2_netigen;

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
