package pl.netigen.core.netigenapi

import android.os.Bundle
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdSize
import pl.netigen.billingandroid.IPaymentManager
import pl.netigen.billingandroid.PaymentManager
import pl.netigen.billingandroid.PurchaseListener
import pl.netigen.core.R
import pl.netigen.core.ads.AdsManager
import pl.netigen.core.rewards.RewardsListener
import java.lang.NullPointerException

abstract class NetigenMainActivity<ViewModel : NetigenViewModel> : AppCompatActivity(), PurchaseListener {

    open lateinit var viewModel: ViewModel
    lateinit var paymentManager: IPaymentManager
    private var bannerRelativeLayout: RelativeLayout? = null
    var adsManager: AdsManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewID())
        initPayments()
        initAdsManager()
    }

    abstract fun getContentViewID(): Int

    private fun initPayments() {
        paymentManager = PaymentManager.createIPaymentManager(this)
        paymentManager.isItemPurchased(viewModel.noAdsSku, this)
    }

    private fun initAdsManager() {
        if (!viewModel.isNoAdsBought) {
            adsManager = AdsManager(viewModel, this)
            if (viewModel.config.rewardedAdId != null) {
                if (prepareRewardedAdsListener() == null)
                    throw NullPointerException("Trying to load rewardedAds without a callback, prepareRewardedAdsListener should be overriden")
                adsManager?.loadRewardedVideo()
            }
        }
    }

    open fun prepareRewardedAdsListener(): RewardsListener? = null

    @CallSuper
    override fun onItemNotBought(sku: String?) {
        if (!sku.isNullOrEmpty() && sku == viewModel.noAdsSku) {
            showAds()
            showBanner()
            adsManager?.loadInterstitialIfPossible()
        }
    }

    abstract fun showAds()

    private fun showBanner() {
        bannerRelativeLayout = getBannerRelativeLayout()
        val layoutParams = bannerRelativeLayout?.layoutParams
        val bannerHeightPixels = AdSize.SMART_BANNER.getHeightInPixels(this)
        layoutParams?.width = RelativeLayout.LayoutParams.MATCH_PARENT
        layoutParams?.height = bannerHeightPixels
        bannerRelativeLayout?.setGravity(Gravity.TOP)
        bannerRelativeLayout?.setLayoutParams(layoutParams)
    }

    @CallSuper
    override fun onPaymentsError(errorMsg: String?) {
        Toast.makeText(this, this.getString(R.string.payment_error) + " $errorMsg", Toast.LENGTH_LONG)
                .show()
    }

    @CallSuper
    override fun onItemBought(sku: String?) {
        if (!sku.isNullOrEmpty() && sku == viewModel.noAdsSku) {
            hideAds()
            viewModel.isNoAdsBought = true
            hideBanner()
        }
    }

    abstract fun hideAds()

    private fun hideBanner() {
        bannerRelativeLayout = getBannerRelativeLayout()
        val layoutParams = bannerRelativeLayout?.layoutParams
        layoutParams?.width = RelativeLayout.LayoutParams.MATCH_PARENT
        layoutParams?.height = 1
        bannerRelativeLayout?.apply {
            gravity = Gravity.TOP
        }
        bannerRelativeLayout?.layoutParams = layoutParams
    }

    abstract fun getBannerRelativeLayout(): RelativeLayout


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

}