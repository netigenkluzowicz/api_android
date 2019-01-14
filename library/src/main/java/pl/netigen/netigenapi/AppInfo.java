package pl.netigen.netigenapi;

import com.google.gson.annotations.SerializedName;

public class AppInfo {
    public final boolean isAd;
    @SerializedName("package_name")
    public final String packageName;
    @SerializedName("name")
    public final String appName;
    public final String iconLink;
    public final String fullAdLink;
    public final int version;

    AppInfo(String packageName, String appName, String iconLink, String fullAdLink, boolean isAd, int version) {
        this.packageName = packageName;
        this.appName = appName;
        this.iconLink = iconLink;
        this.fullAdLink = fullAdLink;
        this.isAd = isAd;
        this.version = version;
    }

    @Override
    public String toString() {
        return "AppInfo(" + packageName + ";" + appName + ";" + iconLink + ";" + fullAdLink + ";" + isAd + ")";
    }
}
