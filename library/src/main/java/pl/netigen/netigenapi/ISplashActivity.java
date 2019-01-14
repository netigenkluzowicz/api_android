package pl.netigen.netigenapi;

import android.content.Intent;


interface ISplashActivity {

    /**
     * Set up LoadProgressListener when loading additional resources is needed or return false otherwise
     * for example BaseResourcesLoader loader = new BaseResourcesLoader(this);
     *
     * @return true - when splash activity must wait for resources load finished or false otherwise
     */
    //
    boolean setUpLoaderClass();

    /**
     * @return Intent(Activity) to launch after initialization
     */
    Intent getIntentToLaunch();

}
