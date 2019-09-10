package pl.netigen.cross_ads

import android.content.Context
import android.util.Log


internal class PromotedAppPrefHelper(val context: Context) {

    private var sharedPreferences = context.getSharedPreferences(PROMOTED_APP_PREFERENCES_NAME, Context.MODE_PRIVATE)


    private fun getPromotedAppPackageName(): String? {
        return sharedPreferences.getString(PROMOTE_PACKAGE_NAME, defaultPackageName)
    }

    private fun getPromotedAppIconPath(): String? {
        return sharedPreferences.getString(PROMOTED_ICON_LINK, null)
    }

    private fun updatePromotedAppPackageName(packageName: String?) {
        if (packageName == null) return
        val editor = sharedPreferences.edit()
        editor.putString(PROMOTE_PACKAGE_NAME, packageName)
        editor.apply()
    }

    private fun updatePromotedAppIconLink(iconLink: String?) {
        val editor = sharedPreferences.edit()
        if (iconLink == null) return
        editor.putString(PROMOTED_ICON_LINK, iconLink)
        editor.apply()
    }

    private fun getLastCallTime(): Long {
        return sharedPreferences.getLong(LAST_UPDATE_KEY, 0)
    }

    private fun updateLastCallTime() {
        val editor = sharedPreferences.edit()
        editor.putLong(LAST_UPDATE_KEY, System.currentTimeMillis())
        editor.apply()
    }

    fun shouldUpdatePromotedIcon(): Boolean {
        val dayOfLastCall = getLastCallTime() / (1000 * 60 * 60 * 24)
        val dayOfToday = System.currentTimeMillis() / (1000 * 60 * 60 * 24)
        return (dayOfToday - dayOfLastCall) > 0L
    }

    fun getSavedCrossAdModel(): PromotedAppModel {
        val crossAdModel = PromotedAppModel()
        crossAdModel.iconLink = getPromotedAppIconPath()
        crossAdModel.promotePackageName = getPromotedAppPackageName()
        Log.d("PromotedAppPrefHelper", " getSavedCrossAdModel: iconLink ${crossAdModel.iconLink} + packageName: ${crossAdModel.promotePackageName}");
        return crossAdModel
    }

    fun updatePromotedApp(promotedAppModel: PromotedAppModel?) {
        updateLastCallTime()
        updatePromotedAppIconLink(promotedAppModel?.iconLink)
        updatePromotedAppPackageName(promotedAppModel?.packageName)
    }

    companion object {
        var defaultPackageName = "pl.netigen.notepad"
        var png = ".png"
        private const val LAST_UPDATE_KEY = "lastUpdateKey"
        private const val PROMOTED_APP_PREFERENCES_NAME = "promote"
        private const val PROMOTE_PACKAGE_NAME = "promote_packagename"
        private const val PROMOTED_ICON_LINK = "promote_icon"

    }
}
