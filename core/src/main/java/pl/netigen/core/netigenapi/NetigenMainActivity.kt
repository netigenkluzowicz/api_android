package pl.netigen.core.netigenapi

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.ads.consent.ConsentInformation
import com.google.android.gms.ads.AdSize
import pl.netigen.billingandroid.IPaymentManager
import pl.netigen.billingandroid.PaymentManager
import pl.netigen.billingandroid.PurchaseListener
import pl.netigen.core.ads.AdsManager
import pl.netigen.core.rewards.RewardsListener

abstract class NetigenMainActivity<ViewModel : NetigenViewModel> : AppCompatActivity(), PurchaseListener {

    open lateinit var viewModel: ViewModel
    private lateinit var paymentManager: IPaymentManager
    private var bannerRelativeLayout: RelativeLayout? = null
    var adsManager: AdsManager? = null
    lateinit var consentInformation: ConsentInformation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewID())
        initPayments()
        observeNoAds()
        setConsentInformation()
    }

    private fun setConsentInformation() {
        consentInformation = ConsentInformation.getInstance(this)
        viewModel.isInEea = consentInformation.isRequestLocationInEeaOrUnknown
    }

    abstract fun getContentViewID(): Int

    private fun initPayments() {
        paymentManager = PaymentManager.createIPaymentManager(this)
    }

    private fun observeNoAds() {
        viewModel.noAdsLiveData.observe(this, Observer {
            if (it) {
                hideAds()
                hideBanner()
                adsManager = null
            }
        })
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
    override fun onItemNotBought(sku: String?) {
        if (!sku.isNullOrEmpty() && sku == viewModel.noAdsSku) {
            adsManager?.loadInterstitialIfPossible()
        }
    }

    abstract fun showAds()

    fun showBanner() {
        bannerRelativeLayout = getBannerRelativeLayout()
        val layoutParams = bannerRelativeLayout?.layoutParams
        val bannerHeightPixels = AdSize.SMART_BANNER.getHeightInPixels(this)
        layoutParams?.width = RelativeLayout.LayoutParams.MATCH_PARENT
        layoutParams?.height = bannerHeightPixels
        bannerRelativeLayout?.gravity = Gravity.TOP
        bannerRelativeLayout?.layoutParams = layoutParams
        getBannerDividerView()?.visibility = View.VISIBLE
    }

    @CallSuper
    override fun onItemBought(sku: String?) {
        if (!sku.isNullOrEmpty() && sku == viewModel.noAdsSku) {
            onNoAdsBought()
        }
    }

    private fun onNoAdsBought() {
        hideBanner()
        hideAds()
        viewModel.isNoAdsBought = true
    }

    @CallSuper
    override fun onPaymentsError(errorMsg: String?) {
        onNoAdsNotBought()
    }

    private fun onNoAdsNotBought() {
        viewModel.isNoAdsBought = false
    }

    fun initAdsManager() {
        if (!viewModel.isNoAdsBought) {
            adsManager = AdsManager(viewModel, this)
            if (viewModel.config.rewardedAdId != null) {
                if (prepareRewardedAdsListener() == null)
                    throw NullPointerException("Trying to load rewardedAds without a callback, prepareRewardedAdsListener should be overriden")
                adsManager?.initRewardedVideoAd(prepareRewardedAdsListener()!!)
                adsManager?.loadRewardedVideo()
            }
        }
    }

    open fun prepareRewardedAdsListener(): RewardsListener? = null

    fun checkIfNoAdsBought() {
        paymentManager.isItemPurchased(viewModel.noAdsSku, this)
    }

    fun initiateNoAdsPayment() {
        paymentManager.initiatePurchase(viewModel.noAdsSku, this, this)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isNoAdsBought) {
            hideBanner()
        } else {
            adsManager?.onResume(getBannerRelativeLayout())
        }
    }

    override fun onPause() {
        super.onPause()
        if (!viewModel.isNoAdsBought) {
            adsManager?.onPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!viewModel.isNoAdsBought) {
            adsManager?.onDestroy()
        }
    }
}