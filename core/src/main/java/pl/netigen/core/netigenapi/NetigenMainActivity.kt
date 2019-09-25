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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewID())
        adsManager = AdsManager(viewModel, this)
//        paymentManager = PaymentManager.createIPaymentManager(this)
//        paymentManager.isItemPurchased(viewModel.noAdsSku, this)
    }

    abstract fun getContentViewID(): Int

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