package pl.netigen.core.language.info;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import pl.netigen.core.R;
import pl.netigen.core.utils.BaseDialogFragment;
import pl.netigen.extensions.ViewTintExtensionKt;

public class TranslationInfoDialogFragment extends BaseDialogFragment {

    private DialogClickListener dialogClickListener;
    private TranslationInfoParams translationInfoParams;
    private TextView buttonChangeLanguageOk;
    private TextView buttonChangeLanguageDismiss;

    private TranslationInfoDialogFragment(int layout) {
        super(layout);
    }

    public interface DialogClickListener {
        void onNegativeButtonClicked();

        void onPositiveButtonClicked();
    }

    public static TranslationInfoDialogFragment newInstance(TranslationInfoParams translationInfoParams) {
        TranslationInfoDialogFragment fragment = new TranslationInfoDialogFragment(R.layout.dialog_fragment_translation_info);
        fragment.translationInfoParams = translationInfoParams;
        fragment.dialogClickListener = translationInfoParams.dialogClickListener;
        return fragment;
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
        buttonChangeLanguageOk = view.findViewById(R.id.button_positive);
        buttonChangeLanguageOk.setText(translationInfoParams.positiveButtonResId);
        buttonChangeLanguageOk.setOnClickListener(v -> {
            if (dialogClickListener != null) {
                dialogClickListener.onPositiveButtonClicked();
            }
            dismiss();
        });
    }

    private void setNegativeButton(View view) {
        buttonChangeLanguageDismiss = view.findViewById(R.id.button_negative);
        buttonChangeLanguageDismiss.setText(translationInfoParams.negativeButtonResId);

        buttonChangeLanguageDismiss.setOnClickListener(v -> {
            if (dialogClickListener != null) {
                dialogClickListener.onNegativeButtonClicked();
            }
            dismiss();
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        setButtonsBackgroundTints();
    }

    private void setButtonsBackgroundTints() {
        Context context = getContext();
        if (context != null) {
            ViewTintExtensionKt.setTint(buttonChangeLanguageOk.getBackground(), context, R.color.dialog_accent, PorterDuff.Mode.MULTIPLY);
            ViewTintExtensionKt.setTint(buttonChangeLanguageDismiss.getBackground(), context, R.color.dialog_neutral_button_bg, PorterDuff.Mode.MULTIPLY);
        }
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
