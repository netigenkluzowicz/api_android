package pl.netigen.netigenapi;


public class Const {

    public static final String TAG = "wrobel";
    public static final int sourceAdWidth = 2048;
    public static final int sourceAdHeight = 1536;
    public static final String API_LINK = "http://api.games.netigen.pl/v2/games/";
    public static final String SOURCES_LINK = "http://games.netigen.pl";
    public static final float sourceRatio = sourceAdWidth / (float) sourceAdHeight;
    public static final String ICONS_PATH = "icon.";
    public static final String ADS_PATH = "ad.";
    public static final int MAX_ICON_SIZE = 300;
    public static final String NO_ADS = "NO_ADS";
    //dialogs const
    public static final int ABOUT_DIALOG = 0;
    public static final int RATE_US_DIALOG = 1;
    public static final String PREFERENCES_NAME = "PREFERENCES_NAME";
    public static final String SHAREDPREFERENCES = "MoreApps";
    public static final String MOREAPPS = "MoreAppsImages";
    public static final int MOREAPPSNUMBER = 8;
    // EDITABLE
    public static final String SAMSUNG = "samsung";

    public static boolean isSamsung() {
        return BuildConfig.FLAVOR.equals(SAMSUNG);
    }


}
