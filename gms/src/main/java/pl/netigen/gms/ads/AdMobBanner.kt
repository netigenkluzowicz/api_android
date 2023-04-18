package pl.netigen.gms.ads

import android.app.Activity
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
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
 *
 * See: [Adaptive Banners](https://developers.google.com/admob/android/banner/adaptive)
 * @property enabled Indicates is current ad active
 */
class AdMobBanner(
    private var activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val bannerLayoutIdName: String,
    override var enabled: Boolean = true,
) : IBannerAd, LifecycleObserver {
    private var bannerView: AdView? = null
    private var loadedBannerOrientation = -1
    private val disabled get() = !enabled
    private lateinit var bannerLayout: RelativeLayout

    init {
        Timber.d("xxx.+()")
        activity.lifecycle.addObserver(this)
    }

    override fun getHeightInPixels(): Int = getAdSize().getHeightInPixels(activity)
    override fun onCreate(activity: AppCompatActivity) {
        if (activity != this.activity) {
            destroyBanner()
            this.activity = activity
        }
        activity.lifecycle.addObserver(this)
    }


    private fun getAdSize(): AdSize {
        val display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        val adWidthPixels = outMetrics.widthPixels.toFloat()

        val adWidth = (adWidthPixels / density).toInt()
        return if (loadedBannerOrientation != ORIENTATION_PORTRAIT) {
            Timber.d("xxx.+ORIENTATION_LANDSCAPE")
            AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(activity, adWidth)
        } else {
            Timber.d("xxx.+ORIENTATION_PORTRAIT")
            AdSize.getPortraitAnchoredAdaptiveBannerAdSize(activity, adWidth)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        Timber.d("xxx.+()")
        bannerLayout = activity.findViewById(activity.resources.getIdentifier(bannerLayoutIdName, "id", activity.packageName))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Timber.d("xxx.+()")
        if (disabled) {
            return
        }
        if (loadedBannerOrientation != activity.resources.configuration.orientation ||
            bannerLayout.childCount == 0 || bannerLayout.getChildAt(0) !== bannerView
        ) {
            loadBanner()
        }
        bannerView?.resume()
    }

    private fun loadBanner() {
        Timber.d("xxx.+()")
        if (disabled) {
            return
        }
        if (loadedBannerOrientation != activity.resources.configuration.orientation || bannerView == null) {
            destroyBanner()
        }
        if (bannerLayout.childCount == 0 || bannerLayout.getChildAt(0) !== bannerView) {
            createBanner()
        }
        loadAd()
    }

    private fun loadAd() {
        Timber.d("xxx.+()")
        bannerView?.loadAd(adMobRequest.getAdRequest())
    }

    private fun createBanner() {
        Timber.d("xxx.+()")
        bannerView = (bannerView ?: AdView(activity)).also {
            bannerLayout.addView(it)
            setBannerLayoutParams(it)
            it.setAdSize(getAdSize())
            it.adUnitId = adId
            loadedBannerOrientation = activity.resources.configuration.orientation
        }
    }


    private fun destroyBanner() {
        Timber.d("xxx.+()")
        val view = bannerView ?: return
        (view.parent as ViewGroup).removeAllViews()
        view.destroy()
        bannerView = null
    }

    private fun setBannerLayoutParams(adView: AdView) {
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        adView.layoutParams = params
        adView.requestLayout()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        Timber.d("xxx.+()")
        bannerView?.pause()
        val parent: ViewParent? = bannerView?.parent
        if (disabled && parent is ViewGroup) parent.removeView(bannerView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() = destroyBanner()
}
