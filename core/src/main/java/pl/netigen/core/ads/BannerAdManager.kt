package pl.netigen.core.ads

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.netigen.core.netigenapi.NetigenViewModel

class BannerAdManager(var viewModel: NetigenViewModel, val activity: Activity) {

    lateinit var bannerView: AdView
    private var loadedBannerOrientation = 0

    fun onBannerAdResume(relativeLayout: RelativeLayout) {
        if (viewModel.isNoAdsBought()) {
            return
        }
        if (bannerView != null || loadedBannerOrientation != activity.getResources().getConfiguration().orientation) {
            loadBanner()
        }
        if (relativeLayout.childCount == 0 || relativeLayout.getChildAt(0) !== bannerView) {
            addView(relativeLayout, bannerView)
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
                RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)

        adView.layoutParams = params
        adView.requestLayout()
    }

    fun onBannerAdPause() {
        if (viewModel.isNoAdsBought()) {
            if (bannerView != null) {
                val parent = bannerView.getParent() as ViewGroup
                parent?.removeView(bannerView)
            }
            return
        }
        if (bannerView != null) {
            bannerView.pause()
            val parent = bannerView.getParent() as ViewGroup
            parent?.removeView(bannerView)
        }
    }

    private fun loadBanner() {
        if (viewModel.isNoAdsBought()) {
            return
        }
        if (bannerView == null || loadedBannerOrientation != activity.getResources().getConfiguration().orientation) {
            loadedBannerOrientation = activity.getResources().getConfiguration().orientation
            bannerView = AdView(activity)
            bannerView.setAdSize(AdSize.SMART_BANNER)
            bannerView.setAdUnitId(viewModel.getBannerId())
        }
        bannerView.loadAd(getAdRequest())
    }

    private fun getAdRequest(): AdRequest {
        val builder = AdRequest.Builder()
        if (viewModel.isInDebugMode()) {
            builder.addTestDevice("F1F415DDE480395A4D21C26D6C6A9619")
                    .addTestDevice("9F65EEB1B6AED06CBE01CFEDA106BD29")
                    .addTestDevice("0F4B0296B48D2C6478D7E9A89DDD07F8")
                    .addTestDevice("593C1BC2C754805F5EFBCD8D4E288805")
                    .addTestDevice("E4347B3256669EAB2235222F475C8492")
                    .addTestDevice("0BFB00BFF8850BE0B8D40286BEDF317E")
                    .addTestDevice("59E58CCD5AB9F4ED41033F114E088239")
                    .addTestDevice("E42C3769BD763551CAC733B6AD662C0D")
                    .addTestDevice("14D51CBB5AB10BDC1FF61BAECA19C9AA")
                    .addTestDevice("8A575BCD3FC92B5719700193610FF48D")
                    .addTestDevice("8B1306F1E7DBD7B656E55F89DFC222D7")
                    .addTestDevice("3F520FF0CA7D49829C8E876C954D8E3D")
                    .addTestDevice("CFB9B2A279566BB6577918A8A8C8F849")
                    .addTestDevice("65364CA3C9CF87116F1D374660DF1235")
                    .addTestDevice("209772FAC421F8EC3FF0D98B7A72FAD2")
                    .addTestDevice("14D51CBB5AB10BDC1FF61BAECA19C9AA")
                    .addTestDevice("3D1FCDD4B6DC7E453846A04D214FD12D")
                    .addTestDevice("43AAFCE5A6B9E8FCDC58E58087AEC4EF")
                    .addTestDevice("AD2180512DE8B1EE611AB4645A69E470")
                    .addTestDevice("379BED7628AE4885B439939575F9F292")
                    .addTestDevice("15E1CF40903FB9938FFBFDBA8A9076E5")

            val testDevices = viewModel.getTestDevices()
            if (testDevices != null) {
                for (i in testDevices!!.indices) {
                    builder.addTestDevice(testDevices!!.get(i))
                }
            }
        }
        if (ConsentInformation.getInstance(activity).consentStatus == ConsentStatus.NON_PERSONALIZED) {
            val extras = Bundle()
            extras.putString("npa", "1")
            return builder.addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                    .build()
        }

        return builder.build()
    }
}
