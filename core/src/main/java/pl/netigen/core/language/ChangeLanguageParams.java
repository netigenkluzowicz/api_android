package pl.netigen.core.language;

import androidx.annotation.StringRes;

import java.util.List;

import pl.netigen.core.R;

public class ChangeLanguageParams {
    @StringRes
    public int titleResId = R.string.change_language_netigen;
    @StringRes
    public int negativeButtonResId = R.string.cancel_netigen;
    @StringRes
    public int positiveButtonResId = R.string.ok_netigen;
    String jsonLanguageCodes;
    List<String> languageCodes;
    ChangeLanguageDialogFragment.LanguageClickListener languageClickListener;

    public ChangeLanguageParams() {
    }

    public ChangeLanguageParams(int titleResId, int negativeButtonResId, int positiveButtonResId,
                                String jsonLanguageCodes, List<String> languageCodes,
                                ChangeLanguageDialogFragment.LanguageClickListener languageClickListener) {
        if (titleResId != 0) this.titleResId = titleResId;
        if (negativeButtonResId != 0) this.negativeButtonResId = negativeButtonResId;
        if (positiveButtonResId != 0) this.positiveButtonResId = positiveButtonResId;
        this.titleResId = titleResId;
        this.jsonLanguageCodes = jsonLanguageCodes;
        this.languageCodes = languageCodes;
        this.languageClickListener = languageClickListener;
    }
}
