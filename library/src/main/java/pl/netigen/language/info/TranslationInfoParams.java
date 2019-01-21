package pl.netigen.language.info;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import pl.netigen.netigenapi.R;

public class TranslationInfoParams {
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

    public TranslationInfoDialogFragment.DialogClickListener dialogClickListener;

    public TranslationInfoParams(int titleResId, int negativeButtonResId,
                                 int positiveButtonResId, int textContentTopResId,
                                 int textContentBottomResId,
                                 @NonNull TranslationInfoDialogFragment.DialogClickListener dialogClickListener) {
        if (titleResId != 0) this.titleResId = titleResId;
        if (negativeButtonResId != 0) this.negativeButtonResId = negativeButtonResId;
        if (positiveButtonResId != 0) this.positiveButtonResId = positiveButtonResId;
        if (textContentTopResId != 0) this.textContentTopResId = textContentTopResId;
        if (textContentBottomResId != 0) this.textContentBottomResId = textContentBottomResId;
        this.dialogClickListener = dialogClickListener;
    }

    public TranslationInfoParams() {

    }
}
