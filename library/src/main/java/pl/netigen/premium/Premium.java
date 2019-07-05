package pl.netigen.premium;

import android.view.Display;
import android.view.View;

import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Premium {

    int bgImage;
    int titleImage;
    int bgPremiumItem;
    int imageButtonBuy;
    int premiumOptionsTitle;
    int textBuyButton;
    Display display;
    String price;
    List<PremiumItem> premiumItems;
    View.OnClickListener clickBuyPremium;

    private Premium(int bgImage, int titleImage, int bgPremiumItem, int imageButtonBuy,

                    int premiumOptionsTitle, int textBuyButton, String price, List<PremiumItem> premiumItems,

                    View.OnClickListener clickBuyPremium,Display display) {
        this.bgImage = bgImage;
        this.titleImage = titleImage;
        this.bgPremiumItem = bgPremiumItem;
        this.imageButtonBuy = imageButtonBuy;
        this.premiumOptionsTitle = premiumOptionsTitle;
        this.textBuyButton = textBuyButton;
        this.price = price;
        this.premiumItems = premiumItems;
        this.clickBuyPremium = clickBuyPremium;
        this.display = display;
    }

    public void openPremiumFragment(@NonNull FragmentManager fragmentManager,
                                    int frameId, String profile) {

        PremiumFragment premiumFragment = PremiumFragment.newInstance(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, premiumFragment, profile);
        transaction.addToBackStack(null);
        transaction.commit();
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
        Display display;
        String price;
        List<PremiumItem> premiumItems;
        View.OnClickListener clickBuyPremium;

        public Builder(int bgImage, int premiumOptionsTitle, int textBuyButton, String price, List<PremiumItem> premiumItems, Display display) {

            this.bgImage = bgImage;
            this.premiumOptionsTitle = premiumOptionsTitle;
            this.textBuyButton = textBuyButton;
            this.price = price;
            this.premiumItems = premiumItems;

            this.display = display;
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

        public Builder setClickBuyPremium(View.OnClickListener clickBuyPremium) {
            this.clickBuyPremium = clickBuyPremium;
            return this;
        }

        public Premium createObject() {
            return new Premium(bgImage, titleImage, bgPremiumItem, imageButtonBuy,
                    premiumOptionsTitle, textBuyButton, price, premiumItems, clickBuyPremium,display);

        }
    }
}
