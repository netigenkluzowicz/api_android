package pl.netigen.viewpager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.netigen.netigenapi.R;


public class ViewPagerFragment extends Fragment {


    public static final int RATING = 0;
    public static final int MORE_APPS = 1;
    private static int currentItem = 0;
    private Fragment moreAppsFragment;

    public static void setCurrentItem(int currentItem) {
        if (currentItem < 0 || currentItem > 1) {
            currentItem = 0;
        }
        ViewPagerFragment.currentItem = currentItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_pager, container, false);
        ViewPager viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
        viewPager.setCurrentItem(currentItem);
        return view;

    }

    @NonNull
    private Fragment getMoreAppsFragment() {
        if (moreAppsFragment == null) {
            moreAppsFragment = new MoreAppsFragment();
        }
        return moreAppsFragment;
    }

    public void setMoreAppsFragment(Fragment moreAppsFragment) {
        this.moreAppsFragment = moreAppsFragment;
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        private static final int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case RATING:
                    return new RatingFragment();
                case MORE_APPS:
                    return getMoreAppsFragment();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case RATING:
                    return getResources().getString(R.string.information);
                case MORE_APPS:
                    return getResources().getString(R.string.more_apps);
            }
            return "";
        }

    }
}
