package pl.netigen.rateus;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

public class RateUsBuilder {
    private AppCompatActivity appCompatActivity;
    @StringRes
    private int titleResId;
    @StringRes
    private int appNameResId;
    @StringRes
    private int askForRateUsInfoId;
    @StringRes
    private int notAskAgainResId;
    @StringRes
    private int positiveResId;
    @StringRes
    private int neutralResId;
    @DrawableRes
    private int appIconResId;

    public RateUsBuilder(AppCompatActivity appCompatActivity, @DrawableRes int appIconResId) {
        this.appCompatActivity = appCompatActivity;
        this.appIconResId = appIconResId;
    }

    public RateUsBuilder setTitleResId(int titleResId) {
        this.titleResId = titleResId;
        return this;
    }

    public RateUsBuilder setAppNameResId(int appNameResId) {
        this.appNameResId = appNameResId;
        return this;
    }

    public RateUsBuilder setAskForRateUsInfoId(int askForRateUsInfoId) {
        this.askForRateUsInfoId = askForRateUsInfoId;
        return this;
    }

    public RateUsBuilder setNotAskAgainResId(int notAskAgainResId) {
        this.notAskAgainResId = notAskAgainResId;
        return this;
    }

    public RateUsBuilder setPositiveResId(int positiveResId) {
        this.positiveResId = positiveResId;
        return this;
    }

    public RateUsBuilder setNeutralResId(int neutralResId) {
        this.neutralResId = neutralResId;
        return this;
    }

    public RateUs createRateUs() {
        return new RateUs(appCompatActivity, titleResId, appNameResId, askForRateUsInfoId, notAskAgainResId, positiveResId, neutralResId, appIconResId);
    }
}