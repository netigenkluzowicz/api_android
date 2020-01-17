package pl.netigen.gms.ads

import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.netigen.coreapi.ads.AdId
import pl.netigen.coreapi.ads.IBannerAd

class AdMobBanner(
    private val activity: AppCompatActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: AdId<String>,
    override val bannerRelativeLayout: RelativeLayout,
    private val isAdaptiveBanner: Boolean = true,
    override var enabled: Boolean = true
) : IBannerAd, LifecycleObserver {
    private var bannerView: AdView = AdView(activity)
    private var loadedBannerOrientation = 0
    private val disabled get() = !enabled
    private val adSize: AdSize = getAdSize()

    init {
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

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        if (disabled) {
            return
        }
        if (loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadBanner()
        }
        val layout = bannerRelativeLayout
        if (layout.childCount == 0 || layout.getChildAt(0) !== bannerView) {
            addView(layout, bannerView)
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
            bannerView.adUnitId = adId.id
        }
        bannerView.loadAd(adMobRequest.getAdRequest())
    }

    private fun addView(layout: RelativeLayout, adView: AdView) {
        if (adView.parent != null) {
            (adView.parent as ViewGroup).removeView(adView)
        }
        layout.addView(adView)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
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
}
