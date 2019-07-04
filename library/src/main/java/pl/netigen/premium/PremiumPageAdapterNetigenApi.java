package pl.netigen.premium;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import pl.netigen.netigenapi.R;


public class PremiumPageAdapterNetigenApi extends PagerAdapter {
    private ImageView image;
    private TextView title;
    private TextView text;
    private ImageView background;
    private int bgItem;
    private List<PremiumItemNetigenApi> premiumItemNetigenApis;

    public PremiumPageAdapterNetigenApi(List<PremiumItemNetigenApi> premiumItemNetigenApis, int bgItem) {
        this.premiumItemNetigenApis = premiumItemNetigenApis;
        this.bgItem = bgItem;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_premium_list_netign_api, null);
        bindView(view);
        bind(position, view);
        container.addView(view);
        return view;
    }

    private void bindView(View view) {
        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        text = view.findViewById(R.id.text);
        background = view.findViewById(R.id.background);
    }

    private void bind(int position, View view) {
        PremiumItemNetigenApi item = premiumItemNetigenApis.get(position);
        if (item != null) {
            title.setText(item.getTitle());
            text.setText(item.getNote());
            Glide.with(view).load(bgItem).into(background);
            Glide.with(view).load(item.getDrawable()).into(image);
        }
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (premiumItemNetigenApis == null) {
            return 0;
        }
        return premiumItemNetigenApis.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

}