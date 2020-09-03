package pl.netigen.premium;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.List;

public class PremiumFragment extends AppCompatDialogFragment {

    private int bgImage;
    private int titleImage;
    private int bgPremiumItem;
    private int imageButtonBuy;
    private int premiumOptionsTitle;
    private int textBuyButton;
    private Point point;

    private List<PremiumItem> premiumItems;
    private CarouselEffectTransformer transformer;
    private View.OnClickListener clickBuyPremium;

    private Premium premiumBuilder;
    private ViewPager viewPager;
    private ImageView backgroundPremium;
    private ImageView premiumTitleImage;
    private TextView buyPremiumButton;
    private AppCompatTextView textAboutPremium;

    private int pageMargin;
    private int viewPagerPadding;

    public PremiumFragment() {
    }

    public static PremiumFragment newInstance(Premium premiumBuilder) {
        PremiumFragment fragment = new PremiumFragment();

        fragment.setArguments(premiumBuilder);
        return fragment;
    }

    private void setArguments(Premium premiumBuilder) {
        this.premiumBuilder = premiumBuilder;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premium_netigen_api, container, false);
        initValues();
        bindView(view);
        addImageAndText();
        calculationsValues();
        createViewPager();
        return view;
    }

    private void initValues() {
        point = new Point();
        if (premiumBuilder != null) {
            bgImage = premiumBuilder.bgImage;
            titleImage = premiumBuilder.titleImage;
            bgPremiumItem = premiumBuilder.bgPremiumItem;
            imageButtonBuy = premiumBuilder.imageButtonBuy;
            premiumOptionsTitle = premiumBuilder.premiumOptionsTitle;
            textBuyButton = premiumBuilder.textBuyButton;
            premiumItems = premiumBuilder.premiumItems;
            clickBuyPremium = premiumBuilder.clickBuyPremium;
            premiumBuilder.display.getSize(point);
        } else {
            dismiss();
        }

    }

    private void calculationsValues() {
        float density = getResources().getDisplayMetrics().density;
        int widthScale = getResources().getInteger(R.integer.widthScalePremiumNetigenApi);
        int marginScale = getResources().getInteger(R.integer.marginScalePremiumNetigenApi);
        int partialWidth = (int) (widthScale * density); // 16dp
        pageMargin = (int) (marginScale * density); // 8dp
        viewPagerPadding = partialWidth + pageMargin;
        float startOffset = (float) (viewPagerPadding) / (float) (point.x - 2 * viewPagerPadding);
        transformer = new CarouselEffectTransformer(0, 0, 0.5f, startOffset);
    }

    private void createViewPager() {
        viewPager.setPageMargin(pageMargin);
        viewPager.setPadding(viewPagerPadding, 0, viewPagerPadding, 0);
        viewPager.setPageTransformer(false, transformer);
        PremiumPageAdapter adapter = new PremiumPageAdapter(premiumItems, bgPremiumItem);
        viewPager.setAdapter(adapter);
    }

    private void bindView(View inflate) {
        viewPager = inflate.findViewById(R.id.viewPager);
        backgroundPremium = inflate.findViewById(R.id.backgroundPremium);
        premiumTitleImage = inflate.findViewById(R.id.premiumTitleImage);
        buyPremiumButton = inflate.findViewById(R.id.buyPremiumButton);
        textAboutPremium = inflate.findViewById(R.id.textAboutPremium);
        if (clickBuyPremium != null) {
            buyPremiumButton.setOnClickListener(clickBuyPremium);
        }
    }


    private void addImageAndText() {
        if (titleImage != 0)
            Glide.with(this).load(titleImage).into(premiumTitleImage);
        if (bgImage != 0)
            Glide.with(this).load(bgImage).into(backgroundPremium);
        if (premiumOptionsTitle != 0)
            textAboutPremium.setText(premiumOptionsTitle);
        if (imageButtonBuy != 0)
            buyPremiumButton.setBackgroundResource(imageButtonBuy);
        if (textBuyButton != 0) {
            String buyText = getString(textBuyButton) + " " + premiumBuilder.price;
            buyPremiumButton.setText(buyText);
        }
    }

}
