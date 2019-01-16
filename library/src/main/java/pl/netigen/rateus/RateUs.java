package pl.netigen.rateus;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import pl.netigen.netigenapi.Utils;

import static android.content.Context.MODE_PRIVATE;

public class RateUs {
    private static final String SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs";
    private static final String KEY_NUMBER_OF_OPENINGS = "KEY_NUMBER_OF_OPENINGS";
    private static final String KEY_IS_RATE_US_OPEN = "KEY_IS_RATE_US_OPEN";
    private static final int NUMBER_OF_CLICKS_BEFORE_SHOWING_DIALOG = 4;
    int titleResId;
    int appNameResId;
    int askForRateUsInfoId;
    int negativeResId;
    int positiveResId;
    int notAskAgainResId;
    int appIconResId;
    private SharedPreferences sharedPreferences;
    private AppCompatActivity appCompatActivity;

    public RateUs(AppCompatActivity appCompatActivity, int titleResId,
                  int appNameResId, int askForRateUsInfoId,
                  int negativeResId, int positiveResId,
                  int notAskAgainResId, int appIconResId) {
        this.appCompatActivity = appCompatActivity;
        this.titleResId = titleResId;
        this.appNameResId = appNameResId;
        this.askForRateUsInfoId = askForRateUsInfoId;
        this.negativeResId = negativeResId;
        this.positiveResId = positiveResId;
        this.notAskAgainResId = notAskAgainResId;
        this.appIconResId = appIconResId;
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
        sharedPreferences = this.appCompatActivity.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (openRateUs()) {
            int open = getNumberOfOpenings();
            addOpen();
            if (open % NUMBER_OF_CLICKS_BEFORE_SHOWING_DIALOG == NUMBER_OF_CLICKS_BEFORE_SHOWING_DIALOG - 1) {
                RateFragment.newInstance(this).show(appCompatActivity.getSupportFragmentManager(), "");
                return false;
            }
        }
        return true;
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
}
