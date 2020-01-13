package pl.netigen.core.netigenapi

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import pl.netigen.core.rewards.RewardsListener
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.payments.INoAds

abstract class NetigenMainActivity<ViewModel : NetigenMainVM> : AppCompatActivity() {

    open lateinit var viewModel: ViewModel
    lateinit var noAds: INoAds
    private var bannerRelativeLayout: RelativeLayout? = null
    var admobAds: IAds? = null
    lateinit var consentInformation: ConsentInformation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setConsentInformation()
        setContentView(getContentViewID())
        initPayments()
        observeNoAds()
        if (viewModel.isDesignedForFamily) {
            onDesignForFamily()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStartActivity()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isNoAdsBought) {
            hideBanner()
        } else {
            if (viewModel.isDesignedForFamily) {
                showBanner()
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (admobAds == null && !viewModel.isNoAdsBought && consentInformation.consentStatus != ConsentStatus.UNKNOWN) {
            initAdsManager()
            admobAds?.let {
                //todo   it.rewardedAdManager?.reloadAd()
            }
            showBanner()
        }
    }

    private fun setConsentInformation() {
        consentInformation = ConsentInformation.getInstance(this)
    }

    abstract fun getContentViewID(): Int

    private fun initPayments() {
        noAds = TODO()
    }

    private fun observeNoAds() {
        viewModel.noAdsLiveData.observe(this, Observer {
            if (it) {
                onNoAdsBought()
            }
        })
    }

    private fun onDesignForFamily() {
        initAdsManager()
        consentInformation.consentStatus = ConsentStatus.NON_PERSONALIZED
    }

    abstract fun hideAds()

    fun hideBanner() {
        bannerRelativeLayout = getBannerRelativeLayout()
        val layoutParams = bannerRelativeLayout?.layoutParams
        layoutParams?.width = RelativeLayout.LayoutParams.MATCH_PARENT
        layoutParams?.height = 1
        bannerRelativeLayout?.apply { gravity = Gravity.TOP }
        bannerRelativeLayout?.layoutParams = layoutParams
        getBannerDividerView()?.visibility = View.GONE
    }

    abstract fun getBannerRelativeLayout(): RelativeLayout

    abstract fun getBannerDividerView(): View?

    @CallSuper
    fun onItemNotBought(sku: String?) {
        if (!sku.isNullOrEmpty() && sku == viewModel.noAdsSku) {
            onNoAdsNotBought()
            admobAds?.interstitialAd?.loadInterstitialAd()
        }
    }

    private fun onNoAdsNotBought() {
        viewModel.isNoAdsBought = false
    }

    abstract fun showAds()

    fun showBanner() {
        bannerRelativeLayout = getBannerRelativeLayout()
        val layoutParams = bannerRelativeLayout?.layoutParams
        val bannerHeightPixels = admobAds?.bannerAd?.getHeightInPixels(this)
        layoutParams?.width = RelativeLayout.LayoutParams.MATCH_PARENT
        layoutParams?.height = bannerHeightPixels
        bannerRelativeLayout?.gravity = Gravity.TOP
        bannerRelativeLayout?.layoutParams = layoutParams
        getBannerDividerView()?.visibility = View.VISIBLE
    }

    @CallSuper
    fun onItemBought(sku: String?) {
        if (!sku.isNullOrEmpty() && sku == viewModel.noAdsSku) {
            viewModel.isNoAdsBought = true
        }
    }

    private fun onNoAdsBought() {
        hideAds()
        hideBanner()
        admobAds = null
    }

    @CallSuper
    fun onPaymentsError(errorMsg: String?) {
        onNoAdsNotBought()
    }

    @CallSuper
    open fun initAdsManager() {
        if (!viewModel.isNoAdsBought) {
            /*todo
         admobAds = AdmobAds(viewModel, this, getBannerRelativeLayout())
         if (viewModel.config.rewardedAdId != null) {
             if (prepareRewardedAdsListener() == null)
                 throw NullPointerException("Trying to load rewardedAds without a callback, prepareRewardedAdsListener should be overriden")
         admobAds?.initRewardedVideoAd(prepareRewardedAdsListener()!!)
             admobAds?.loadRewardedVideo()
            }*/
        }
    }

    open fun prepareRewardedAdsListener(): RewardsListener? = null

    fun checkIfNoAdsBought() {
        // TODO: 13.01.2020  
    }

    fun initiateNoAdsPayment() {
        // TODO: 13.01.2020  
    }

    fun canCommitFragments(): Boolean {
        return viewModel.canCommitFragments
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStopActivity()
    }

}