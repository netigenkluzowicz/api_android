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
import pl.netigen.coreapi.ads.IBannerAd
import pl.netigen.coreapi.main.ICoreMainActivity
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
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val bannerLayoutIdName: String,
    override var enabled: Boolean = true,
) : IBannerAd, LifecycleObserver {
    private var bannerView: AdView? = null
    private var loadedBannerOrientation = -1
    private val disabled get() = !enabled
    private var currentActivity: ComponentActivity = activity
    private val bannerLayout: RelativeLayout?
        get() = (currentActivity as ICoreMainActivity).bannerView()


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


    private fun getAdSize(): AdSize {
        val display = currentActivity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        return if (currentActivity.resources.configuration.orientation != ORIENTATION_LANDSCAPE) {
            val adWidthPixels = outMetrics.widthPixels.toFloat()
            val adWidth = (adWidthPixels / density).toInt()
            AdSize.getPortraitAnchoredAdaptiveBannerAdSize(currentActivity, adWidth)
        } else {
            val maxWidth = maxOf(outMetrics.heightPixels.toFloat(), outMetrics.widthPixels.toFloat())
            val adWidth = (maxWidth / density).toInt()
            AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(currentActivity, adWidth)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Timber.d("xxx.+()")
        if (disabled) return

        val bannerLayout1 = bannerLayout ?: return
        if (loadedBannerOrientation != currentActivity.resources.configuration.orientation || bannerView == null ||
            bannerLayout1.childCount == 0 || bannerLayout1.getChildAt(0) !== bannerView
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
        val bannerLayout1 = bannerLayout ?: return
        if (bannerLayout1.childCount == 0 || bannerLayout1.getChildAt(0) !== bannerView || bannerView == null) {
            createBanner()
        }
        loadAdMob()
    }

    private fun loadAdMob() {
        Timber.d("xxx.+()")
        bannerView?.loadAd(adMobRequest.getAdRequest())
    }

    private fun createBanner() {
        createAdmob()
    }


    private fun createAdmob() {
        val bannerLayout1 = bannerLayout ?: return
        bannerView = (bannerView ?: AdView(currentActivity)).also {
            bannerLayout1.addView(it)
            setBannerLayoutParams(it)
            val adSize = getAdSize()
            it.setAdSize(adSize)
            it.adUnitId = adId
            loadedBannerOrientation = currentActivity.resources.configuration.orientation
        }
    }


    private fun destroyBanner() {
        Timber.d("xxx.+()")
        currentActivity.runOnUiThread {
            val view = bannerView.also { it?.destroy() } ?: return@runOnUiThread
            (view.parent as ViewGroup?)?.removeAllViews()
            bannerView = null
        }
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
