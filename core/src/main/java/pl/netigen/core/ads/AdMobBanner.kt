package pl.netigen.core.ads

import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.netigen.coreapi.ads.IBannerAd

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
        activity.lifecycle.addObserver(this)
    }

    override fun hideBanner() {
        bannerView.pause()
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
        bannerView = AdView(activity)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        bannerLayout = activity.findViewById(activity.resources.getIdentifier(bannerLayoutIdName, "id", activity.packageName))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
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
        if (adView.parent != null) {
            (adView.parent as ViewGroup).removeView(adView)
        }
        layout.addView(adView)
        setBannerLayoutParams(adView)
    }

    private fun setBannerLayoutParams(adView: AdView, height: Int = RelativeLayout.LayoutParams.WRAP_CONTENT) {
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height)
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        adView.layoutParams = params
        adView.requestLayout()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        if (disabled) {
            val parent = bannerView.parent as ViewGroup
            parent.removeView(bannerView)
            return
        }
        bannerView.pause()
        val parent = bannerView.parent as ViewGroup
        parent.removeView(bannerView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        bannerView.destroy()
    }

}
