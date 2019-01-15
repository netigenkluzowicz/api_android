package pl.netigen.viewpager;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import pl.netigen.netigenapi.Config;
import pl.netigen.netigenapi.MoreAppItem;
import pl.netigen.netigenapi.R;
import pl.netigen.netigenapi.Utils;

public class MoreAppsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private final static String MORE_APPS_JSON = "moreapps/moreapps.json";
    private final static String MORE_APPS_JSON_SAMSUNG = "moreapps/moreapps_samsung.json";
    private MoreAppItem[] moreApps;
    private MoreAppItem[] localMoreAppItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.more_app);
        moreApps = getLocalMoreAppsList();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.BLACK);
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        if (moreApps != null) {
            for (MoreAppItem moreAppItem : moreApps) {
                Preference preference = new Preference(getActivity());
                preference.setIcon(new BitmapDrawable(getResources(), moreAppItem.getIconBitmap(getResources())));
                preference.setKey(moreAppItem.packageName);
                preference.setTitle(moreAppItem.appName);
                preferenceScreen.addPreference(preference);
            }
        }

        for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
            preferenceScreen.getPreference(i).setOnPreferenceClickListener(this);
        }
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference != null && preference.getKey() != null && preference.getKey().length() > 3) {
            Utils.openMarketLink(getActivity(), preference.getKey());
        }
        return true;
    }

    public MoreAppItem[] getLocalMoreAppsList() {
        if (localMoreAppItems == null) {
            try {
                String moreAppsJson = Config.isSamsung() ? MORE_APPS_JSON_SAMSUNG : MORE_APPS_JSON;
                InputStream inputStream = getActivity().getAssets().open(moreAppsJson);
                Gson gson = new Gson();
                localMoreAppItems = gson.fromJson(new InputStreamReader(inputStream), MoreAppItem[].class);
                return localMoreAppItems;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return localMoreAppItems;
    }
}