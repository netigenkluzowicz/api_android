package pl.netigen.premium;

import android.view.View;

import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PremiumBuilderNetigenApi {

    int bgImage;
    int titleImage;
    int bgPremiumItem;
    int imageButtonBuy;
    int premiumOptionsTitle;
    int textBuyButton;
    String price;
    List<PremiumItemNetigenApi> premiumItemNetigenApis;
    View.OnClickListener clickBuyPremium;

    private PremiumBuilderNetigenApi(int bgImage, int titleImage, int bgPremiumItem, int imageButtonBuy,
                                     int premiumOptionsTitle, int textBuyButton, String price, List<PremiumItemNetigenApi> premiumItemNetigenApis,
                                     View.OnClickListener clickBuyPremium) {
        this.bgImage = bgImage;
        this.titleImage = titleImage;
        this.bgPremiumItem = bgPremiumItem;
        this.imageButtonBuy = imageButtonBuy;
        this.premiumOptionsTitle = premiumOptionsTitle;
        this.textBuyButton = textBuyButton;
        this.price = price;
        this.premiumItemNetigenApis = premiumItemNetigenApis;
        this.clickBuyPremium = clickBuyPremium;
    }

    public void openPremiumFragment(@NonNull FragmentManager fragmentManager,
                                    int frameId, String profile) {
        testValues();
        PremiumFragmentNetigenApi premiumFragmentNetigenApi = PremiumFragmentNetigenApi.newInstance(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, premiumFragmentNetigenApi, profile);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void testValues() {
        if (bgImage == 0) {
            throw new IllegalArgumentException("You must add bgImage");
        }
        if (premiumOptionsTitle == 0) {
            throw new IllegalArgumentException("You must add premiumOptionsTitle");
        }
        if (textBuyButton == 0) {
            throw new IllegalArgumentException("You must add textBuyButton");
        }
        if (price == null) {
            throw new IllegalArgumentException("You must add price");
        }
        if (premiumItemNetigenApis == null) {
            throw new IllegalArgumentException("You must add premiumItemNetigenApis");
        }
    }

    public static class Builder {
        @DrawableRes
        int bgImage;
        @DrawableRes
        int titleImage;
        @DrawableRes
        int bgPremiumItem;
        @DrawableRes
        int imageButtonBuy;
        @StringRes
        int premiumOptionsTitle;
        @StringRes
        int textBuyButton;
        String price;
        List<PremiumItemNetigenApi> premiumItemNetigenApis;
        View.OnClickListener clickBuyPremium;

        public Builder() {
        }

        public Builder setBgImage(int bgImage) {
            this.bgImage = bgImage;
            return this;
        }

        public Builder setTitleImage(int titleImage) {
            this.titleImage = titleImage;
            return this;
        }

        public Builder setBgPremiumItem(int bgPremiumItem) {
            this.bgPremiumItem = bgPremiumItem;
            return this;
        }

        public Builder setImageButtonBuy(int imageButtonBuy) {
            this.imageButtonBuy = imageButtonBuy;
            return this;
        }

        public Builder setPremiumOptionsTitle(int premiumOptionsTitle) {
            this.premiumOptionsTitle = premiumOptionsTitle;
            return this;
        }

        public Builder setTextBuyButton(int textBuyButton) {
            this.textBuyButton = textBuyButton;
            return this;
        }

        public Builder setPremiumItemListNetigenApis(List<PremiumItemNetigenApi> premiumItemNetigenApis) {
            this.premiumItemNetigenApis = premiumItemNetigenApis;
            return this;
        }

        public Builder setClickBuyPremium(View.OnClickListener clickBuyPremium) {
            this.clickBuyPremium = clickBuyPremium;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public PremiumBuilderNetigenApi createObject() {
            return new PremiumBuilderNetigenApi(bgImage, titleImage, bgPremiumItem, imageButtonBuy,
                    premiumOptionsTitle, textBuyButton, price, premiumItemNetigenApis, clickBuyPremium);
        }
    }
}
