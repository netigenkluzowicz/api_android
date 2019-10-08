package pl.netigen.core.ads

import android.app.Activity
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.netigen.core.netigenapi.NetigenViewModel

class BannerAdManager(var viewModel: NetigenViewModel, val activity: Activity, val adsManager: AdsManager) {

    var bannerView: AdView? = null
    private var loadedBannerOrientation = 0

    private fun loadBanner() {
        if (viewModel.isNoAdsBought) {
            return
        }
        if (bannerView == null || loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadedBannerOrientation = activity.resources.configuration.orientation
            bannerView = AdView(activity)
            bannerView?.adSize = AdSize.SMART_BANNER
            bannerView?.adUnitId = viewModel.getBannerId()
        }
        bannerView?.loadAd(adsManager.getAdRequest())
    }

    fun onResume(relativeLayout: RelativeLayout) {
        if (viewModel.isNoAdsBought) {
            return
        }
        if (bannerView != null || loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadBanner()
        }
        if (relativeLayout.childCount == 0 || relativeLayout.getChildAt(0) !== bannerView) {
            addView(relativeLayout, bannerView)
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
                RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)

        adView?.layoutParams = params
        adView?.requestLayout()
    }

    fun onPause() {
        if (viewModel.isNoAdsBought) {
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
