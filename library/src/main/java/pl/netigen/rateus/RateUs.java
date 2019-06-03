package pl.netigen.rateus;


import android.content.SharedPreferences;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import pl.netigen.netigenapi.R;
import pl.netigen.netigenapi.Utils;

import static android.content.Context.MODE_PRIVATE;

public class RateUs {
    private static final String SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs";
    private static final String KEY_NUMBER_OF_OPENINGS = "KEY_NUMBER_OF_OPENINGS";
    private static final String KEY_IS_RATE_US_OPEN = "KEY_IS_RATE_US_OPEN";
    private static final int NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG = 4;
    int titleResId;
    int appNameResId;
    int askForRateUsInfoId;
    int negativeResId;
    int positiveResId;
    int notAskAgainResId;
    int appIconResId;
    int clicksBeforeShowingRateUs;
    private SharedPreferences sharedPreferences;
    private AppCompatActivity appCompatActivity;
    boolean isDefaultPopUp;
    int customLayoutId;

    private RateUs(AppCompatActivity appCompatActivity, int titleResId,
                   int appNameResId, int askForRateUsInfoId,
                   int negativeResId, int positiveResId,
                   int notAskAgainResId, int appIconResId, int clicksBeforeShowingRateUs,
                   boolean isDefaultPopUp) {
        this.appCompatActivity = appCompatActivity;
        this.titleResId = titleResId;
        this.appNameResId = appNameResId;
        this.askForRateUsInfoId = askForRateUsInfoId;
        this.negativeResId = negativeResId;
        this.positiveResId = positiveResId;
        this.notAskAgainResId = notAskAgainResId;
        this.appIconResId = appIconResId;
        this.sharedPreferences = this.appCompatActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        this.isDefaultPopUp = isDefaultPopUp;
        if (clicksBeforeShowingRateUs != 0) {
            this.clicksBeforeShowingRateUs = clicksBeforeShowingRateUs;
        } else {
            this.clicksBeforeShowingRateUs = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG;
        }
    }

    private RateUs(AppCompatActivity appCompatActivity, int titleResId,
                   int askForRateUsInfoId, int negativeResId, int positiveResId,
                   int notAskAgainResId, int clicksBeforeShowingRateUs,
                   int customLayoutId, boolean isDefaultPopUp) {
        this.appCompatActivity = appCompatActivity;
        this.titleResId = titleResId;
        this.askForRateUsInfoId = askForRateUsInfoId;
        this.negativeResId = negativeResId;
        this.positiveResId = positiveResId;
        this.customLayoutId = customLayoutId;
        this.notAskAgainResId = notAskAgainResId;
        this.sharedPreferences = this.appCompatActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        this.isDefaultPopUp = isDefaultPopUp;
        if (clicksBeforeShowingRateUs != 0) {
            this.clicksBeforeShowingRateUs = clicksBeforeShowingRateUs;
        } else {
            this.clicksBeforeShowingRateUs = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG;
        }
    }

    private int getNumberOfOpenings() {
        return sharedPreferences.getInt(KEY_NUMBER_OF_OPENINGS, 0);
    }

    private void updateNumberOfOpenings() {
        sharedPreferences.edit().putInt(KEY_NUMBER_OF_OPENINGS, getNumberOfOpenings() + 1).apply();
    }

    private boolean getIsRateUsOpen() {
        return sharedPreferences.getBoolean(KEY_IS_RATE_US_OPEN, true);
    }

    private void setIsRateUsOpen(boolean b) {
        sharedPreferences.edit().putBoolean(KEY_IS_RATE_US_OPEN, b).apply();
    }

    private void addOpen() {
        updateNumberOfOpenings();
    }

    public boolean openRateDialogIfNeeded() {
        if (openRateUs()) {
            int open = getNumberOfOpenings();
            addOpen();
            if (open % clicksBeforeShowingRateUs == clicksBeforeShowingRateUs - 1) {
                openRateDialog();
                return true;
            }
        }
        return false;
    }

    public void openRateDialog() {
        RateFragment.newInstance(this).show(appCompatActivity.getSupportFragmentManager(), "");
    }

    private boolean openRateUs() {
        return getIsRateUsOpen();
    }

    void clickYes() {
        notAskAgain();
        Utils.openMarketLink(appCompatActivity, appCompatActivity.getPackageName());
    }


    void clickNo() {
        notAskAgain();
    }

    private void notAskAgain() {
        setIsRateUsOpen(false);
    }

    void clickLater() {

    }

    public static class Builder {
        private AppCompatActivity appCompatActivity;
        @StringRes
        private int titleResId = R.string.rate_us;
        @StringRes
        private int appNameResId;
        @StringRes
        private int askForRateUsInfoId = R.string.do_you_like_this_app_and_want_to_support_us_we_will_be_grateful_give_us_5_stars;
        @StringRes
        private int notAskAgainResId = R.string.no_exclamation_mark;
        @StringRes
        private int positiveResId = R.string.yes_exclamation_mark;
        @StringRes
        private int neutralResId = R.string.later_exclamation_mark;
        @DrawableRes
        private int appIconResId;
        private int clicksBeforeShowingRateUs = NUMBER_OF_CHECKS_BEFORE_SHOWING_DIALOG;
        private boolean isDefaultPopUp;
        private int customLayoutId;

        public Builder(AppCompatActivity appCompatActivity, int appNameResId, @DrawableRes int appIconResId) {
            this.appCompatActivity = appCompatActivity;
            this.appNameResId = appNameResId;
            this.appIconResId = appIconResId;
            isDefaultPopUp = true;
        }

        public Builder(AppCompatActivity appCompatActivity, int customLayoutId) {
            this.appCompatActivity = appCompatActivity;
            isDefaultPopUp = false;
            this.customLayoutId = customLayoutId;
        }

        public Builder setTitleResId(int titleResId) {
            this.titleResId = titleResId;
            return this;
        }

        public Builder setAskForRateUsInfoId(int askForRateUsInfoId) {
            this.askForRateUsInfoId = askForRateUsInfoId;
            return this;
        }

        public Builder setNotAskAgainResId(int notAskAgainResId) {
            this.notAskAgainResId = notAskAgainResId;
            return this;
        }

        public Builder setPositiveResId(int positiveResId) {
            this.positiveResId = positiveResId;
            return this;
        }

        public Builder setNeutralResId(int neutralResId) {
            this.neutralResId = neutralResId;
            return this;
        }

        public Builder setClicksBeforeShowingDialog(int numberOfClicksBeforeShwoingDialog) {
            clicksBeforeShowingRateUs = numberOfClicksBeforeShwoingDialog;
            return this;
        }

        public RateUs createRateUs() {
            if (isDefaultPopUp)
                return new RateUs(appCompatActivity, titleResId, appNameResId, askForRateUsInfoId, notAskAgainResId, positiveResId, neutralResId, appIconResId, clicksBeforeShowingRateUs, isDefaultPopUp);
            else
                return new RateUs(appCompatActivity, titleResId, askForRateUsInfoId, notAskAgainResId, positiveResId, neutralResId, clicksBeforeShowingRateUs, customLayoutId, isDefaultPopUp);

        }
    }
}
