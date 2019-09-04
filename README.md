https://jitpack.io/#netigenkluzowicz/api_android

<h1>All project Strings are updated by adding a library name</h1>

<h1>Current develop branch is Kotlin branch</h1>
We strongly recommend working on Kotlin branch only for now.
2.x branches are AndroidX migration branches
1.x branches are old Android support branches

## Add premium fragment to application:

You need to add this code to your Splash activity:

```Java
    @Override
    public void onPurchasedItemsLoaded(List<Purchase> purchases) {
        List<String> skuList = new ArrayList<>();
        skuList.add(ConstFlavours.NO_ADS_SKU);
        paymentManager.getSkuDetailsList(skuList, (responseCode, skuDetailsList) -> {
            if (responseCode.getResponseCode() == BillingClient.BillingResponseCode.OK
                    && skuDetailsList != null) {
                setSkuDetailsList(skuDetailsList);
            }
        });
    }
    
    public void setSkuDetailsList(List<SkuDetails> skuDetailsList) {
        if (skuDetailsList == null || skuDetailsList.size() == 0) {
            return;
        }
        SkuDetails skuDetails = skuDetailsList.get(0);
        if (skuDetails != null) {
            Const.PRICE = skuDetails.getPrice();
        }

    }
```

Add to the activity in which you open the fragment:

```Java
public void openPremiumFragment() {
        int bgImage = R.drawable.bg_uni_premium; 
        int titleImage = R.drawable.premium_title_; 
        int bgPremiumItem = R.drawable.bg_ttext; 
        int imageButtonBuy = R.drawable.btn_buy; 
        int premiumOptionsTitle = R.string.choose_the_premium_options;
        int textBuyButton = R.string.buy_premium_for;
        List<PremiumItem> premiumItems = addList();
        View.OnClickListener clickBuyPremium = v -> paymentManager.initiatePurchase(ConstFlavours.NO_ADS_SKU, this, MainActivity.this);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Premium premium = new Premium.Builder(bgImage, premiumOptionsTitle, textBuyButton, Const.PRICE, premiumItems, defaultDisplay)
                .setBgPremiumItem(bgPremiumItem)
                .setTitleImage(titleImage)
                .setImageButtonBuy(imageButtonBuy)
                .setClickBuyPremium(clickBuyPremium)
                .createObject();
        premium.openPremiumFragment(fragmentManager, R.id.fullSizeFrame, "PremiumFragment");
}
        
private List<PremiumItem> addList() {
        List<PremiumItem> premiumItems = new ArrayList<>();
        PremiumItem premiumItem1 = new PremiumItem(R.drawable.no_ads, "Get rid of ads", "No more ads in the app!");
        premiumItems.add(premiumItem1);
        .
        .
        .
        .
        .
        return premiumItems;
}
