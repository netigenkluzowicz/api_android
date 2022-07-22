package pl.netigen.core.newlanguage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import java.util.*

object ChangeLanguagePreferences {
    const val LANGUAGE_PREFERENCES = "LANGUAGE_PREFERENCES"
    private const val KEY_USER_LOCALE = "KEY_USER_LOCALE"

    lateinit var sharedPreferences: SharedPreferences


    fun setActivityLocale(activity: Activity) {
        setSharedPreferences(activity)
        val savedLanguageCode = preferencesLocale
        if (savedLanguageCode != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (activity.resources.configuration.locales.get(0).language != preferencesLocale) {
                    setLocale(savedLanguageCode, activity)
                }
            } else {
                if (activity.resources.configuration.locale.language != preferencesLocale) {
                    setLocale(savedLanguageCode, activity)
                }
            }


        }
    }

    fun setLocale(lang: String?, context: Context) {
        if (lang == null) return
        preferencesLocale = lang
        val myLocale = Locale(lang)
        val res = context.resources
        val conf = res.configuration
        conf.setLocale(myLocale)
        context.createConfigurationContext(conf)
    }

    fun setSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(LANGUAGE_PREFERENCES, Context.MODE_PRIVATE)
    }

    var preferencesLocale: String
        get() {
            return sharedPreferences.getString(KEY_USER_LOCALE, Locale.getDefault().language) ?: "en"
        }
        set(userLocale) {
            sharedPreferences.edit().putString(KEY_USER_LOCALE, userLocale).apply()
        }


    fun getCurrentAppLocale(context: Context): String {
        val res = context.resources
        val dm = res.displayMetrics
        val conf = res.configuration
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            conf.locales.get(0).language
        } else {
            conf.locale.language
        }
    }

}