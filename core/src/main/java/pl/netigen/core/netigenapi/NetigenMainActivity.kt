package pl.netigen.core.netigenapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.netigen.billingandroid.IPaymentManager
import pl.netigen.billingandroid.PaymentManager
import pl.netigen.billingandroid.PurchaseListener
import pl.netigen.core.ads.AdsManager

abstract class NetigenMainActivity<ViewModel : NetigenViewModel> : AppCompatActivity(), PurchaseListener {

    lateinit var adsManager: AdsManager
    lateinit var paymentManager: IPaymentManager

    open lateinit var viewModel: ViewModel
    lateinit var paymentManager: IPaymentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewID())
        adsManager = AdsManager(viewModel, this)
        initPayments()
    }

    abstract fun getContentViewID(): Int

    private fun initPayments() {
        paymentManager = PaymentManager.createIPaymentManager(this)
        paymentManager.isItemPurchased(viewModel.noAdsSku, this)
    }
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