package pl.netigen.core.admob

import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.netigen.core.ads.AdId
import pl.netigen.core.ads.IBannerAd

class AdmobBanner(
    val activity: AppCompatActivity,
    private val admobAds: AdmobAds,
    override val adId: AdId<String>,
    override var bannerLayout: RelativeLayout,
    override var enabled: Boolean = true
) : IBannerAd, LifecycleObserver {
    private var bannerView: AdView = AdView(activity)
    private var loadedBannerOrientation = 0
    private val disabled get() = !enabled

    init {
        activity.lifecycle.addObserver(this)
    }

    private fun loadBanner() {
        if (disabled) {
            return
        }
        if (loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadedBannerOrientation = activity.resources.configuration.orientation
            bannerView = AdView(activity)
            bannerView.adSize = AdSize.SMART_BANNER
            bannerView.adUnitId = adId.id
        }
        bannerView.loadAd(admobAds.getAdRequest())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        if (disabled) {
            return
        }
        if (loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadBanner()
        }
        val layout = bannerLayout
        if (layout.childCount == 0 || layout.getChildAt(0) !== bannerView) {
            addView(layout, bannerView)
        }
        bannerView.resume()
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
