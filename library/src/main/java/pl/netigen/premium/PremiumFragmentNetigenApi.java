package pl.netigen.premium;


import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import pl.netigen.netigenapi.R;


public class PremiumFragmentNetigenApi extends Fragment {


    private int bgImage;
    private int titleImage;
    private int bgPremiumItem;
    private int imageButtonBuy;
    private int premiumOptionsTitle;
    private int textBuyButton;

    private List<PremiumItemNetigenApi> premiumItemNetigenApis;
    private CarouselEffectTransformer transformer;
    private View.OnClickListener clickBuyPremium;


    private PremiumBuilderNetigenApi premiumBuilder;
    private ViewPager viewPager;
    private ImageView backgroundPremium;
    private ImageView premiumTitleImage;
    private TextView buyPremiumButton;
    private AppCompatTextView textAboutPremium;

    private int pageMargin;
    private int viewPagerPadding;

    public static PremiumFragmentNetigenApi newInstance(PremiumBuilderNetigenApi premiumBuilder) {
        PremiumFragmentNetigenApi fragment = new PremiumFragmentNetigenApi();
        fragment.setArguments(premiumBuilder);
        return fragment;
    }

    private void setArguments(PremiumBuilderNetigenApi premiumBuilder) {
        this.premiumBuilder = premiumBuilder;
    }


    public PremiumFragmentNetigenApi() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_premium_netigen_api, container, false);
        initValues();
        bindView(inflate);
        addImageAndText();
        calculationsValues();
        createViewPager();
        return inflate;
    }

    private void initValues() {
        bgImage = premiumBuilder.bgImage;
        titleImage = premiumBuilder.titleImage;
        bgPremiumItem = premiumBuilder.bgPremiumItem;
        imageButtonBuy = premiumBuilder.imageButtonBuy;
        premiumOptionsTitle = premiumBuilder.premiumOptionsTitle;
        textBuyButton = premiumBuilder.textBuyButton;
        premiumItemNetigenApis = premiumBuilder.premiumItemNetigenApis;
        clickBuyPremium = premiumBuilder.clickBuyPremium;
    }

    private void calculationsValues() {
        float density = getResources().getDisplayMetrics().density;
        int widthScale = getResources().getInteger(R.integer.widthScale);
        int marginScale = getResources().getInteger(R.integer.marginScale);
        int partialWidth = (int) (widthScale * density); // 16dp
        pageMargin = (int) (marginScale * density); // 8dp
        viewPagerPadding = partialWidth + pageMargin;
        Point screen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screen);
        float startOffset = (float) (viewPagerPadding) / (float) (screen.x - 2 * viewPagerPadding);
        transformer = new CarouselEffectTransformer(0, 0, 0.5f, startOffset);

    }

    private void createViewPager() {
        viewPager.setPageMargin(pageMargin);
        viewPager.setPadding(viewPagerPadding, 0, viewPagerPadding, 0);
        viewPager.setPageTransformer(false, transformer);
        PremiumPageAdapterNetigenApi adapter = new PremiumPageAdapterNetigenApi(premiumItemNetigenApis, bgPremiumItem);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {/*empty*/}

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {/*empty*/}
        });
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
