package pl.netigen.gms.ads

import android.content.Context
import android.content.res.Configuration
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.netigen.coreapi.ads.IBannerAd
import pl.netigen.coreapi.main.ICoreMainActivity
import timber.log.Timber
import kotlin.math.max

/**
 * [IBannerAd] implementation backed by [AdView]
 *
 * See: [Banner Ads](https://developers.google.com/admob/android/banner)
 *
 * Registers itself as a [DefaultLifecycleObserver] on the provided
 * [LifecycleOwner] (`ComponentActivity`) to pause/resume/destroy the banner
 * with lifecycle events.
 *
 * @property currentActivity the hosting [ComponentActivity] used as [Context]
 * and as a [LifecycleOwner] for lifecycle callbacks
 * @property adMobRequest provides [com.google.android.gms.ads.AdRequest] for this ad
 * @property adId current ad identifier
 * @property bannerLayoutIdName resource name of the [RelativeLayout] used for banner placement
 * @property enabled whether this ad is currently active
 *
 * See: [Adaptive Banners](https://developers.google.com/admob/android/banner/adaptive)
 */
class AdMobBanner(
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val bannerLayoutIdName: String,
    override var enabled: Boolean = true,
) : IBannerAd, DefaultLifecycleObserver {
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
        val dm = currentActivity.resources.displayMetrics
        val density = dm.density
        val orientation = currentActivity.resources.configuration.orientation

        val adWidthDp = if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            (dm.widthPixels / density).toInt()
        } else {
            (max(dm.widthPixels, dm.heightPixels) / density).toInt()
        }

        return if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            AdSize.getPortraitAnchoredAdaptiveBannerAdSize(currentActivity, adWidthDp)
        } else {
            AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(currentActivity, adWidthDp)
        }
    }

    private fun loadBanner() {
        Timber.d("xxx.+()")
        if (disabled) return

        if (loadedBannerOrientation != currentActivity.resources.configuration.orientation || getHeightInPixels() > getAdSize().height) {
            destroyBanner()
        }
        val bannerLayout1 = bannerLayout ?: return
        if (bannerLayout1.isEmpty() || bannerLayout1.getChildAt(0) !== bannerView || bannerView == null) {
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

    override fun onPause(owner: LifecycleOwner) {
        Timber.d("xxx.+()")
        val adView = bannerView ?: return
        adView.pause()
        val parent = adView.parent as? ViewGroup ?: return
        if (disabled) parent.removeView(adView)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)       // zamiast currentActivity.lifecycle...
        destroyBanner()
    }

    override fun onResume(owner: LifecycleOwner) {
        Timber.d("xxx.+()")
        if (disabled) return

        val bannerLayout1 = bannerLayout ?: return
        val orientation = currentActivity.resources.configuration.orientation
        if (
            loadedBannerOrientation != orientation ||
            bannerView == null ||
            bannerLayout1.isEmpty() ||
            bannerLayout1.getChildAt(0) !== bannerView
        ) {
            loadBanner()
        }
        bannerView?.resume()
    }
}
