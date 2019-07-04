package pl.netigen.premium;

import android.os.Build;
import android.view.View;


import androidx.viewpager.widget.ViewPager;

public class CarouselEffectTransformer implements ViewPager.PageTransformer {

    private int baseElevation;
    private int raisingElevation;
    private float smallerScale;
    private float startOffset;

    CarouselEffectTransformer(int baseElevation, int raisingElevation, float smallerScale, float startOffset) {
        this.baseElevation = baseElevation;
        this.raisingElevation = raisingElevation;
        this.smallerScale = smallerScale;
        this.startOffset = startOffset;
    }

    @Override
    public void transformPage(View page, float position) {
        float absPosition = Math.abs(position - startOffset);

        if (absPosition >= 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                page.setElevation(baseElevation);
            }
            page.setScaleY(smallerScale);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                page.setElevation(((1 - absPosition) * raisingElevation + baseElevation));
            }
            page.setScaleY((smallerScale - 1) * absPosition + 1);
        }
    }
}
