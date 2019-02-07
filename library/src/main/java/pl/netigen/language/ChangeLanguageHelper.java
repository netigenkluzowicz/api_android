package pl.netigen.language;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import pl.netigen.language.info.TranslationInfoDialogFragment;
import pl.netigen.netigenapi.R;

public class ChangeLanguageHelper {

    private static String[] arrayOfProperlyTranslatedLanguages = new String[]{"pl", "pt"/*, "ru"*/, "es", "de", "ko", "en"};
    private static SharedPreferences sharedPreferences;
    public static final String LANGUAGE_PREFERENCES = "LANGUAGE_PREFERENCES";
    private static final String KEY_WAS_TRANSLATION_DIALOG_SHOWN = "KEY_WAS_TRANSLATION_DIALOG_SHOWN";
    private static final String KEY_USER_LOCALE = "KEY_USER_LOCALE";

    public static void setLocaleAndRestartApp(String lang, AppCompatActivity currentActivity, Class activityToLaunch) {
        if (lang == null)
            return;
        setPreferencesLocale(lang);
        Locale myLocale = new Locale(lang);
        Resources res = currentActivity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(currentActivity, activityToLaunch);
        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        currentActivity.startActivity(refresh);
        currentActivity.finish();
    }

    public static void setLocaleAndRecreate(Activity activity) {
        setSharedPreferences(activity);
        String savedLanguageCode = getPreferencesLocale();
        Locale locale = new Locale(savedLanguageCode);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config, null);
        activity.recreate();
    }

    public static void setActivityLocale(Activity activity) {
        setSharedPreferences(activity);
        String savedLanguageCode = getPreferencesLocale();
        if (savedLanguageCode != null) {
            if (!activity.getResources().getConfiguration().locale.getLanguage().equals(getPreferencesLocale())) {
                setLocale(savedLanguageCode, activity);
            }
        }
    }

    public static void setLocale(String lang, Context context) {
        if (lang == null)
            return;
        setPreferencesLocale(lang);
        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public static String getCurrentAppLocale(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        return conf.locale.getLanguage();
    }

    /*
     * Use only if DialogFragment doesn't properly fit the screen
     * */
    public static void showTranslationInfoAlertDialog(TranslationInfoDialogFragment.DialogClickListener dialogClickListener, AppCompatActivity appCompatActivity) {
        View dialogView = appCompatActivity.getLayoutInflater().inflate(R.layout.dialog_fragment_translation_info, null);
        AlertDialog alertDialog = new AlertDialog.Builder(appCompatActivity)
                .setView(dialogView)
                .create();
        Button buttonPositive = dialogView.findViewById(R.id.button_positive);
        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogClickListener.onPositiveButtonClicked();
                alertDialog.dismiss();
            }
        });
        Button buttonNegative = dialogView.findViewById(R.id.button_negative);
        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogClickListener.onNegativeButtonClicked();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void showTranslationInfoDialogIfNeeded(TranslationInfoDialogFragment.Builder builder) {
        sharedPreferences = builder.getActivity().getSharedPreferences(LANGUAGE_PREFERENCES, Context.MODE_PRIVATE);
        List<String> translatedLanguages = Arrays.asList(arrayOfProperlyTranslatedLanguages);
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = builder.getActivity().getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = builder.getActivity().getResources().getConfiguration().locale;
        }
        if (!translatedLanguages.contains(locale.getLanguage())) {
            if (!wasTranslationDialogShown()) {
                builder.show();
                setTranslationDialogShown(true);
            }
        }
    }

    private static boolean wasTranslationDialogShown() {
        return sharedPreferences.getBoolean(KEY_WAS_TRANSLATION_DIALOG_SHOWN, false);
    }

    private static void setTranslationDialogShown(boolean wasDialogShown) {
        sharedPreferences.edit().putBoolean(KEY_WAS_TRANSLATION_DIALOG_SHOWN, wasDialogShown).apply();
    }

    public static String getPreferencesLocale() {
        return sharedPreferences.getString(KEY_USER_LOCALE, Locale.getDefault().getLanguage());
    }

    public static void setPreferencesLocale(String userLocale) {
        sharedPreferences.edit().putString(KEY_USER_LOCALE, userLocale).commit();
    }

    public static void setSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(ChangeLanguageHelper.LANGUAGE_PREFERENCES, Context.MODE_PRIVATE);
    }

    private static final String TAG = "ChangeLanguageHelper";

    private static Context updateResources(Context activityContext, Context applicationContext) {
        sharedPreferences = activityContext.getSharedPreferences(LANGUAGE_PREFERENCES, Context.MODE_PRIVATE);
        String language = getPreferencesLocale();
        Log.d(TAG, "updateResources: language " + language);
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = activityContext.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
            Log.d(TAG, "updateResources: if1 config locale: " + config.getLocales().get(0).getDisplayLanguage());
        } else {
            config.locale = locale;
            Log.d(TAG, "updateResources: else1 config locale: " + config.getLocales().get(0).getDisplayLanguage());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            activityContext.createConfigurationContext(config);
            applicationContext.createConfigurationContext(config);
            Log.d(TAG, "updateResources: if2 config locale: " + activityContext.getResources().getConfiguration().getLocales().get(0).getDisplayLanguage());
        } else {
            res.updateConfiguration(config, res.getDisplayMetrics());
            Log.d(TAG, "updateResources: else2 config locale: " + activityContext.getResources().getConfiguration().getLocales().get(0).getDisplayLanguage());
        }
        Log.d(TAG, "updateResources: after update: " + activityContext.getResources().getConfiguration().getLocales().get(0).getDisplayLanguage());
        return activityContext;
    }

    public static Context setLocale(Context activityContext, Context applicationContext) {
        return updateResources(activityContext, applicationContext);
    }

    public static Context setNewLocale(Context activityContext, Context applicationContext, String language) {
        setPreferencesLocale(language);
        return updateResources(activityContext, applicationContext);
    }
}
