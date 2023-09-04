package pl.netigen.gms.ads

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
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
import com.yandex.mobile.ads.banner.BannerAdView
import pl.netigen.coreapi.ads.IBannerAd
import timber.log.Timber

/**
 * [IBannerAd] implementation with [AdView] from Google Mobile Ads SDK
 *
 * See: [Banner Ads](https://developers.google.com/admob/android/banner)
 *
 * @property currentActivity [ComponentActivity] for this ad placement and [Lifecycle] events
 * @property adMobRequest Provides [AdRequest] for this ad
 * @property adId Current ad [String] identifier
 * @property bannerLayoutIdName Id of [RelativeLayout] for banner ad placement
 *
 * See: [Adaptive Banners](https://developers.google.com/admob/android/banner/adaptive)
 * @property enabled Indicates is current ad active
 */
class AdMobBanner(
    activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val bannerLayoutIdName: String,
    override val yandexAdId: String,
    override var enabled: Boolean = true,
) : IBannerAd, LifecycleObserver {
    private var bannerView: AdView? = null
    private var loadedBannerOrientation = -1
    private val disabled get() = !enabled
    private var currentActivity: ComponentActivity = activity
    private lateinit var bannerLayout: RelativeLayout
    private var bannerYandex: BannerAdView? = null
    private var yanexActive = false

    init {
        Timber.d("xxx.+()")
        activity.lifecycle.addObserver(this)
    }

    override fun getHeightInPixels(): Int = getAdSize().getHeightInPixels(currentActivity)
    override fun onCreate(activity: AppCompatActivity) {
        if (activity != this.currentActivity) {
            destroyBanner()
            currentActivity.lifecycle.removeObserver(this)
            currentActivity = activity
            currentActivity.lifecycle.addObserver(this)
            destroyBanner()
            currentActivity = activity
        }
    }

    override fun enableYandex() {
        destroyBanner()
        yanexActive = true
    }


    private fun getAdSize(): AdSize {
        val display = currentActivity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        return if (currentActivity.resources.configuration.orientation != ORIENTATION_LANDSCAPE) {
            val adWidthPixels = outMetrics.widthPixels.toFloat()
            val adWidth = (adWidthPixels / density).toInt()
            Timber.d("xxx.+ORIENTATION_PORTRAIT" + adWidth)
            AdSize.getPortraitAnchoredAdaptiveBannerAdSize(currentActivity, adWidth)
        } else {
            val maxWidth = maxOf(outMetrics.heightPixels.toFloat(), outMetrics.widthPixels.toFloat())
            val adWidth = (maxWidth / density).toInt()
            Timber.d("xxx.+ORIENTATION_LANDSCAPE" + adWidth)
            AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(currentActivity, adWidth)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        Timber.d("xxx.+()")
        bannerLayout = currentActivity.findViewById(currentActivity.resources.getIdentifier(bannerLayoutIdName, "id", currentActivity.packageName))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Timber.d("xxx.+()")
        if (disabled) return

        if (loadedBannerOrientation != currentActivity.resources.configuration.orientation || bannerView == null ||
            bannerLayout.childCount == 0 || bannerLayout.getChildAt(0) !== bannerView
        ) {
            loadBanner()
        }
        bannerView?.resume()
    }

    private fun loadBanner() {
        Timber.d("xxx.+()")
        if (disabled) return

        if (loadedBannerOrientation != currentActivity.resources.configuration.orientation || getHeightInPixels() > getAdSize().height) {
            destroyBanner()
        }
        if (bannerLayout.childCount == 0 || bannerLayout.getChildAt(0) !== bannerView || bannerView == null) {
            createBanner()
        }
        loadAd()
    }

    private fun loadAd() {
        Timber.d("xxx.+()")
        bannerView?.loadAd(adMobRequest.getAdRequest())
    }

    private fun createBanner() {
        bannerView = (bannerView ?: AdView(currentActivity)).also {
            bannerLayout.addView(it)
            setBannerLayoutParams(it)
            val adSize = getAdSize()
            it.setAdSize(adSize)
            it.adUnitId = adId
            loadedBannerOrientation = currentActivity.resources.configuration.orientation
        }
    }


    private fun destroyBanner() {
        Timber.d("xxx.+()")
        val view = bannerView.also { it?.destroy() } ?: bannerYandex ?: return
        (view.parent as ViewGroup?)?.removeAllViews()
        bannerView = null
        bannerYandex = null
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
        val adView = bannerView ?: return
        adView.pause()
        val parent: ViewParent = adView.parent ?: return
        if (disabled && parent is ViewGroup) parent.removeView(adView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        currentActivity.lifecycle.removeObserver(this)
        destroyBanner()
    }
}
