package pl.netigen.core.ads

import android.app.Activity
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class AdmobBanner(
    val activity: Activity,
    val admobAds: AdmobAds,
    override var bannerLayout: RelativeLayout? = null
) : LifecycleObserver, IBannerAd {
    override var enabled = true
    private var bannerView: AdView? = null
    private var loadedBannerOrientation = 0

    private fun loadBanner() {
        if (enabled) {
            return
        }
        if (bannerView == null || loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadedBannerOrientation = activity.resources.configuration.orientation
            bannerView = AdView(activity)
            bannerView?.adSize = AdSize.SMART_BANNER
            bannerView?.adUnitId = admobAds.bannerId
        }
        bannerView?.loadAd(admobAds.getAdRequest())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        if (enabled) {
            return
        }
        if (bannerView != null || loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadBanner()
        }
        val layout = bannerLayout ?: return
        if (layout.childCount == 0 || layout.getChildAt(0) !== bannerView) {
            addView(layout, bannerView)
        }
        bannerView?.resume()
    }

    private fun addView(layout: RelativeLayout, adView: AdView?) {
        if (adView?.parent != null) {
            (adView.parent as ViewGroup).removeView(adView)
        }
        layout.addView(adView)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)

        adView?.layoutParams = params
        adView?.requestLayout()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        if (enabled) {
            if (bannerView != null) {
                val parent = bannerView?.parent as ViewGroup
                parent.removeView(bannerView)
            }
            return
        }
        if (bannerView != null) {
            bannerView?.pause()
            val parent = bannerView?.parent as ViewGroup
            parent.removeView(bannerView)
        }
    }
}
