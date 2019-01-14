package pl.netigen.viewpager;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Arrays;

import pl.netigen.netigenapi.BaseBannerActivity;
import pl.netigen.netigenapi.Const;
import pl.netigen.netigenapi.R;
import pl.netigen.netigenapi.Utils;

public class DrawerManager {
    private static final int INITIAL_SIZE = 4;
    private ListView drawerMenuList;
    private DrawerLayout drawerMenuLayout;
    private FragmentTransaction fragmentTransaction;
    private ViewPagerFragment viewPagerFragment;
    private boolean canCommitFragments;
    private BaseBannerActivity bannerActivity;
    private View openCloseDrawerMenuView;
    private int fragmentPlaceholderId;
    private final AdapterView.OnItemClickListener drawerMenuClickListener = (parent, view, position, id) -> defaultOnItemClick(position);
    private boolean shouldBeClosed;
    private ArrayList<String> drawerMenuItems;
    private ArrayList<View.OnClickListener> onClickListeners;
    private boolean noAdsAdded;

    public DrawerManager(BaseBannerActivity bannerActivity, ListView drawerMenuList, DrawerLayout drawerMenuLayout, View openCloseDrawerMenuView, int fragmentPlaceholderId, boolean isMainActivity) {
        this.drawerMenuList = drawerMenuList;
        this.drawerMenuLayout = drawerMenuLayout;
        this.bannerActivity = bannerActivity;
        this.openCloseDrawerMenuView = openCloseDrawerMenuView;
        this.fragmentPlaceholderId = fragmentPlaceholderId;
        shouldBeClosed = !isMainActivity;
        initDrawerMenu(bannerActivity);
    }

    public void openAbout() {
        Utils.showDialog(Const.ABOUT_DIALOG, bannerActivity);
    }

    public int defaultOnItemClick(int position) {
        switch (position) {
            case 0:
                Utils.openMarketLink(bannerActivity, bannerActivity.getPackageName());
                closeDrawerMenu();
                break;
            case 1:
                openMoreApps();
                closeDrawerMenu();
                break;
            case 2:
                openRating();
                closeDrawerMenu();
                break;
            case 3:
                Utils.showDialog(Const.ABOUT_DIALOG, bannerActivity);
                closeDrawerMenu();
                break;
            default:
                closeDrawerMenu();
        }
        return position;
    }

    public void addMenuItem(String displayName, final View.OnClickListener onClickListener) {
        drawerMenuItems.add(displayName);
        if (onClickListeners == null) {
            onClickListeners = new ArrayList<>();
        }
        onClickListeners.add(onClickListener);
        this.drawerMenuList.setAdapter(new ArrayAdapter<>(bannerActivity, R.layout.drawer_list_item, drawerMenuItems));
        this.drawerMenuList.setOnItemClickListener((parent, view, position, id) -> {
            if (position >= INITIAL_SIZE) {
                clickAddedItem(view, position);
            } else {
                defaultOnItemClick(position);
            }
        });
    }

    private void clickAddedItem(View view, int position) {
        if (onClickListeners != null) {
            int index = position - INITIAL_SIZE;
            if (index < onClickListeners.size()) {
                onClickListeners.get(index).onClick(view);
                closeDrawerMenu();
            }
        }
    }

    private void initDrawerMenu(Activity activity) {
        drawerMenuItems = new ArrayList<>(Arrays.asList(activity.getResources().getStringArray(R.array.leftMenuItemsArray)));
        this.drawerMenuLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                DrawerManager.this.drawerMenuLayout.bringChildToFront(drawerView);
                DrawerManager.this.drawerMenuLayout.requestLayout();
                DrawerManager.this.drawerMenuLayout.invalidate();
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        this.drawerMenuList.setAdapter(new ArrayAdapter<>(activity, R.layout.drawer_list_item, drawerMenuItems));
        this.drawerMenuList.setOnItemClickListener(drawerMenuClickListener);
        openCloseDrawerMenuView.setOnClickListener(view -> showHideDrawerMenu());
    }

    private void showHideDrawerMenu() {
        if (isDrawerMenuOpen()) {
            closeDrawerMenu();
        } else {
            openDrawerMenu();
        }
    }

    private void openDrawerMenu() {
        drawerMenuLayout.openDrawer(drawerMenuList);
        bannerActivity.onBannerAdPause();
    }

    private void closeDrawerMenu() {
        drawerMenuLayout.closeDrawer(drawerMenuList);
    }

    private boolean isDrawerMenuOpen() {
        return drawerMenuLayout.isDrawerOpen(drawerMenuList);
    }

    private void openViewPager() {
        try {
            if (canCommitFragments) {
                fragmentTransaction = bannerActivity.getFragmentManager().beginTransaction();
                viewPagerFragment = new ViewPagerFragment();
                fragmentTransaction.replace(fragmentPlaceholderId, viewPagerFragment).commit();
            }
        } catch (Exception e) {
        }
    }

    private boolean isViewPagerOpened() {
        return viewPagerFragment != null;
    }

    private void closeViewPagerFragment() {
        if (viewPagerFragment != null && canCommitFragments) {
            fragmentTransaction = bannerActivity.getFragmentManager().beginTransaction();
            fragmentTransaction.remove(viewPagerFragment);
            fragmentTransaction.commit();
            viewPagerFragment = null;
        }
    }

    public void openMoreApps() {
        ViewPagerFragment.setCurrentItem(ViewPagerFragment.MORE_APPS);
        openViewPager();
    }

    public void openRating() {
        ViewPagerFragment.setCurrentItem(ViewPagerFragment.RATING);
        openViewPager();
    }

    public boolean onKeyDown(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                showHideDrawerMenu();
                return true;
            case KeyEvent.KEYCODE_BACK:
                if (isDrawerMenuOpen()) {
                    closeDrawerMenu();
                    return true;
                } else if (shouldBeClosed) {
                    return false;
                } else if (isViewPagerOpened()) {
                    closeViewPagerFragment();
                    return true;
                } else {
                    openRating();
                    shouldBeClosed = true;
                    return true;
                }
        }
        return false;
    }

    public void onStart() {
        canCommitFragments = true;
    }

    public void onStop() {
        canCommitFragments = false;
    }

    public void addDrawerListener(DrawerLayout.DrawerListener drawerListener) {
        this.drawerMenuLayout.addDrawerListener(drawerListener);
    }

    public void removeNoAds() {
        drawerMenuItems.remove(drawerMenuItems.size() - 1);
        if (onClickListeners == null) {
            onClickListeners = new ArrayList<>();
        }
        onClickListeners.remove(onClickListeners.size() - 1);
        this.drawerMenuList.setAdapter(new ArrayAdapter<>(bannerActivity, R.layout.drawer_list_item, drawerMenuItems));
        this.drawerMenuList.setOnItemClickListener((parent, view, position, id) -> {
            if (position >= INITIAL_SIZE) {
                clickAddedItem(view, position);
            } else {
                defaultOnItemClick(position);
            }
        });
    }

    public boolean isSomethingOpened() {
        return isDrawerMenuOpen() || isViewPagerOpened();
    }

    public boolean canCommitFragments() {
        return canCommitFragments;
    }
}
