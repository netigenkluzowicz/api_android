---
title: ChangeLanguageHelper - core
---

[core](../../index.md) / [pl.netigen.core.language](../index.md) / [ChangeLanguageHelper](./index.md)

# ChangeLanguageHelper

`open class ChangeLanguageHelper`

### Constructors

| [&lt;init&gt;](-init-.md)) | `ChangeLanguageHelper()` |

### Properties

| [LANGUAGE_PREFERENCES](-l-a-n-g-u-a-g-e_-p-r-e-f-e-r-e-n-c-e-s.md)) | `static val LANGUAGE_PREFERENCES: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |

### Functions

| [getCurrentAppLocale](get-current-app-locale.md)) | `open static fun getCurrentAppLocale(context: `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!` |
| [getPreferencesLocale](get-preferences-locale.md)) | `open static fun getPreferencesLocale(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!` |
| [setActivityLocale](set-activity-locale.md)) | `open static fun setActivityLocale(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.md))`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setLocale](set-locale.md)) | `open static fun setLocale(lang: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!, context: `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)<br>`open static fun setLocale(activityContext: `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!, applicationContext: `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!): `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!` |
| [setLocaleAndRecreate](set-locale-and-recreate.md)) | `open static fun setLocaleAndRecreate(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.md))`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setLocaleAndRestartApp](set-locale-and-restart-app.md)) | `open static fun setLocaleAndRestartApp(lang: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!, currentActivity: `[`AppCompatActivity`](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatActivity.md))`!, activityToLaunch: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.md))`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.md)`!>!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setNewLocale](set-new-locale.md)) | `open static fun setNewLocale(activityContext: `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!, applicationContext: `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!, language: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!): `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!` |
| [setPreferencesLocale](set-preferences-locale.md)) | `open static fun setPreferencesLocale(userLocale: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setSharedPreferences](set-shared-preferences.md)) | `open static fun setSharedPreferences(context: `[`Context`](https://developer.android.com/reference/android/content/Context.md))`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showTranslationInfoAlertDialog](show-translation-info-alert-dialog.md)) | `open static fun showTranslationInfoAlertDialog(dialogClickListener: DialogClickListener!, appCompatActivity: `[`AppCompatActivity`](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatActivity.md))`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showTranslationInfoDialogIfNeeded](show-translation-info-dialog-if-needed.md)) | `open static fun showTranslationInfoDialogIfNeeded(builder: Builder!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

