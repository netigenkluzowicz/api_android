package pl.netigen.cross_ads

import com.google.gson.annotations.SerializedName
import pl.netigen.crossads.R

class PromotedAppModel {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("package_name")
    var packageName: String? = null

    @SerializedName("promoteAppGraphic")
    var iconLink: String? = null

    @SerializedName("promoteAppPackageName")
    var promotePackageName: String? = null

    companion object {
        const val defaultPromotedAppPackageName = "pl.netigen.notepad"
        val defaultPromotedAppIconResId = R.drawable.default_promoted_app
    }
}
