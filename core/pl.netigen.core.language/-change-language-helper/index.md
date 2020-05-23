---
title: ChangeLanguageHelper - core
---

[core](../../index.md) / [pl.netigen.core.language](../index.md) / [ChangeLanguageHelper](./index.md)

# ChangeLanguageHelper

`open class ChangeLanguageHelper`

### Constructors

| [&lt;init&gt;](-init-.html) | `ChangeLanguageHelper()` |

### Properties

| [LANGUAGE_PREFERENCES](-l-a-n-g-u-a-g-e_-p-r-e-f-e-r-e-n-c-e-s.html) | `static val LANGUAGE_PREFERENCES: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |

### Functions

| [getCurrentAppLocale](get-current-app-locale.html) | `open static fun getCurrentAppLocale(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!` |
| [getPreferencesLocale](get-preferences-locale.html) | `open static fun getPreferencesLocale(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!` |
| [setActivityLocale](set-activity-locale.html) | `open static fun setActivityLocale(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setLocale](set-locale.html) | `open static fun setLocale(lang: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!, context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)<br>`open static fun setLocale(activityContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!, applicationContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!): `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!` |
| [setLocaleAndRecreate](set-locale-and-recreate.html) | `open static fun setLocaleAndRecreate(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setLocaleAndRestartApp](set-locale-and-restart-app.html) | `open static fun setLocaleAndRestartApp(lang: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!, currentActivity: `[`AppCompatActivity`](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatActivity.html)`!, activityToLaunch: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.md)`!>!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setNewLocale](set-new-locale.html) | `open static fun setNewLocale(activityContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!, applicationContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!, language: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!): `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!` |
| [setPreferencesLocale](set-preferences-locale.html) | `open static fun setPreferencesLocale(userLocale: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setSharedPreferences](set-shared-preferences.html) | `open static fun setSharedPreferences(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showTranslationInfoAlertDialog](show-translation-info-alert-dialog.html) | `open static fun showTranslationInfoAlertDialog(dialogClickListener: DialogClickListener!, appCompatActivity: `[`AppCompatActivity`](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatActivity.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showTranslationInfoDialogIfNeeded](show-translation-info-dialog-if-needed.html) | `open static fun showTranslationInfoDialogIfNeeded(builder: Builder!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

