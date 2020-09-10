package pl.netigen.gms.ads

import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.netigen.coreapi.ads.IBannerAd
import timber.log.Timber

/**
 * [IBannerAd] implementation with [AdView] from Google Mobile Ads SDK
 *
 * See: [Banner Ads](https://developers.google.com/admob/android/banner)
 *
 * @property activity [ComponentActivity] for this ad placement and [Lifecycle] events
 * @property adMobRequest Provides [AdRequest] for this ad
 * @property adId Current ad [String] identifier
 * @property bannerLayoutIdName Id of [RelativeLayout] for banner ad placement
 * @property isAdaptiveBanner When true (default value) adaptive banner instead of smart banner is used
 *
 * See: [Adaptive Banners](https://developers.google.com/admob/android/banner/adaptive)
 * @property enabled Indicates is current ad active
 */
class AdMobBanner(
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val bannerLayoutIdName: String,
    private val isAdaptiveBanner: Boolean = true,
    override var enabled: Boolean = true
) : IBannerAd, LifecycleObserver {
    private lateinit var bannerView: AdView
    private var loadedBannerOrientation = 0
    private val disabled get() = !enabled
    private val adSize: AdSize = getAdSize()
    private lateinit var bannerLayout: RelativeLayout

    init {
        Timber.d("()")
        activity.lifecycle.addObserver(this)
    }

    override fun getHeightInPixels(): Int = adSize.getHeightInPixels(activity)

    private fun getAdSize(): AdSize {
        if (!isAdaptiveBanner) return AdSize.SMART_BANNER
        val display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        val adWidthPixels = outMetrics.widthPixels.toFloat()

        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        Timber.d("()")
        bannerView = AdView(activity)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        Timber.d("()")
        bannerLayout = activity.findViewById(activity.resources.getIdentifier(bannerLayoutIdName, "id", activity.packageName))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Timber.d("()")
        if (disabled) {
            return
        }
        if (loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadBanner()
        }
        if (bannerLayout.childCount == 0 || bannerLayout.getChildAt(0) !== bannerView) {
            addView(bannerLayout, bannerView)
        }
        bannerView.resume()
    }

    private fun loadBanner() {
        Timber.d("()")
        if (disabled) {
            return
        }
        if (loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadedBannerOrientation = activity.resources.configuration.orientation
            bannerView = AdView(activity)
            bannerView.adSize = adSize
            bannerView.adUnitId = adId
        }
        bannerView.loadAd(adMobRequest.getAdRequest())
    }

    private fun addView(layout: RelativeLayout, adView: AdView) {
        Timber.d("layout = [$layout], adView = [$adView]")
        if (adView.parent != null) {
            (adView.parent as ViewGroup).removeView(adView)
        }
        layout.addView(adView)
        setBannerLayoutParams(adView)
    }

    private fun setBannerLayoutParams(adView: AdView, height: Int = RelativeLayout.LayoutParams.WRAP_CONTENT) {
        Timber.d("adView = [$adView], height = [$height]")
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height)
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        adView.layoutParams = params
        adView.requestLayout()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        Timber.d("()")
        bannerView.pause()
        val parent: ViewParent? = bannerView.parent
        if (disabled && parent is ViewGroup) parent.removeView(bannerView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        Timber.d("()")
        bannerView.destroy()
    }

}
