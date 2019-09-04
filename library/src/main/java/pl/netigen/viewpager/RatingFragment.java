package pl.netigen.viewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.View;

import pl.netigen.netigenapi.R;
import pl.netigen.netigenapi.Utils;

public class RatingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.rating_layout);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.BLACK);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
            preferenceScreen.getPreference(i).setOnPreferenceClickListener(this);
        }
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals(getString(R.string.rate_us_caps_netigen))) {
            final Activity activity = getActivity();
            if (activity != null) {
                final String packageName = activity.getPackageName();
                Utils.openMarketLink(activity, packageName);
            }
        }
        switch (preference.getKey()) {
            case "website":
                Utils.openLink(getActivity(), getString(R.string.www_url_netigen));
                break;
            case "facebook":
                Utils.openLink(getActivity(), getString(R.string.facebook_url_netigen));
                break;
            case "twitter":
                Utils.openLink(getActivity(), getString(R.string.twitter_url_netigen));
                break;
            case "google":
                Utils.openLink(getActivity(), getString(R.string.google_plus_url_netigen));
                break;
            case "youtube":
                Utils.openLink(getActivity(), getString(R.string.youtube_netigen));
                break;
        }
        return true;
    }
}