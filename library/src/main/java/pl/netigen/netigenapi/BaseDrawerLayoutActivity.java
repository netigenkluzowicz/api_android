package pl.netigen.netigenapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;

import pl.netigen.viewpager.DrawerManager;


public abstract class BaseDrawerLayoutActivity extends BaseBannerActivity implements DrawerLayout.DrawerListener {
    private DrawerManager drawerManager;

    public abstract DrawerManager initDrawerManagerOnCreate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerManager = initDrawerManagerOnCreate();
        drawerManager.addDrawerListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return drawerManager.onKeyDown(keyCode) || super.onKeyDown(keyCode, event);
    }

    public DrawerManager getDrawerManager() {
        return drawerManager;
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        drawerManager.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        drawerManager.onStop();
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        super.onBannerAdPause();
    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {
        super.onBannerAdResume();
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
