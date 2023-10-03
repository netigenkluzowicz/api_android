package pl.netigen.coreapi.ads

import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

/**
 * Manages Banner ad:
 * - lifecycle is connected to activity lifecycle
 * - is placed automatically in given [RelativeLayout] provided in [IAdsConfig.bannerLayoutIdName]
 * - its container is resized when ad size is different than its height, and it is invisible to the user
 *
 * Banner ads (excluding Adaptive Banners) occupy a spot within an app's layout, either at the top or bottom of the device screen.
 * They stay on screen while users are interacting with the app, and can refresh automatically after a certain period of time.
 *
 * Adaptive banners are the next generation of responsive ads, maximizing performance by optimizing ad size for each device.
 * Improving on smart banners, which only supported fixed heights,
 * adaptive banners let developers specify the ad-width and use this to determine the optimal ad size.
 *
 */
interface IBannerAd : IAd {
    /**
     * Measures current banner implementation exact height in pixels
     *
     * @return Banner height in px
     */
    fun getHeightInPixels(): Int
    fun onCreate(activity: AppCompatActivity)
    fun onStart(bannerLayout: RelativeLayout)
}
