package pl.netigen.hms.ads

import android.app.Activity
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.BannerAdSize
import com.huawei.hms.ads.banner.BannerView
import pl.netigen.coreapi.ads.IBannerAd
import timber.log.Timber

class HMSBanner(
    private val activity: ComponentActivity,
    override val adId: String,
    private val bannerLayoutIdName: String,
    override var enabled: Boolean = true,
) : IBannerAd, LifecycleObserver {
    private lateinit var bannerView: BannerView
    private var loadedBannerOrientation = 0
    private val disabled get() = !enabled
    private lateinit var bannerLayout: RelativeLayout

    init {
        Timber.d("()")
        activity.lifecycle.addObserver(this)
    }

    override fun getHeightInPixels(): Int = BannerAdSize.BANNER_SIZE_SMART.getHeightPx(activity)
    override fun onCreate(activity: AppCompatActivity) = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        Timber.d("()")
        bannerView = BannerView(activity)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        Timber.d("()")
        bannerLayout = activity.findViewById(activity.resources.getIdentifier(bannerLayoutIdName, "id", activity.packageName))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Timber.d("()")
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
        Timber.d("()")
        if (disabled) {
            return
        }
        if (loadedBannerOrientation != activity.resources.configuration.orientation) {
            loadedBannerOrientation = activity.resources.configuration.orientation
            bannerView.adId = adId
            bannerView.bannerAdSize = BannerAdSize.BANNER_SIZE_SMART
        }
        bannerView.loadAd(AdParam.Builder().build())
    }

    private fun addView(layout: RelativeLayout, adView: BannerView) {
        Timber.d("layout = [$layout], adView = [$adView]")
        if (adView.parent != null) {
            (adView.parent as ViewGroup).removeView(adView)
        }
        layout.addView(adView)
        setBannerLayoutParams(adView)
    }

    private fun setBannerLayoutParams(adView: BannerView, height: Int = RelativeLayout.LayoutParams.WRAP_CONTENT) {
        Timber.d("adView = [$adView], height = [$height]")
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height)
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        adView.layoutParams = params
        adView.requestLayout()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        Timber.d("()")
        bannerView.pause()
        val parent: ViewParent? = bannerView.parent
        if (disabled && parent is ViewGroup) parent.removeView(bannerView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        Timber.d("()")
        bannerView.destroy()
    }
}
