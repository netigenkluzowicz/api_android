package pl.netigen.rateus;

import android.support.v7.app.AppCompatActivity;

public class RateUsBuilder {
    private AppCompatActivity appCompatActivity;
    private int titleResId;
    private int appNameResId;
    private int askForRateUsInfoId;
    private int notAskAgainResId;
    private int positiveResId;
    private int neutralResId;
    private int appIconResId;

    public RateUsBuilder setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        return this;
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

    public RateUsBuilder setAppIconResId(int appIconResId) {
        this.appIconResId = appIconResId;
        return this;
    }

    public RateUs createRateUs() {
        return new RateUs(appCompatActivity, titleResId, appNameResId, askForRateUsInfoId, notAskAgainResId, positiveResId, neutralResId, appIconResId);
    }
}