package pl.netigen.core.donate

import pl.netigen.core.R
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.utils.Utils
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.coreapi.payments.model.PaymentSuccess
import timber.log.Timber

class Donate {

    fun updateDetails(skuDetails: List<NetigenSkuDetails>) {
        if (skuDetails.isNotEmpty()) {
            donates.clear()
            for ((productId, _, _, _, priceAmountMicros, priceCurrencyCode, _, _, _) in skuDetails) {

                val priceText = String.format("%.2f", priceAmountMicros?.div(1000000.0) ?: 0).replace(",", ".") + " " + priceCurrencyCode
                if (matchesProductId(productId)) {
                    donates.add(DonateInfo(productId, priceText, productId.last().digitToInt()))
                }
            }
            Timber.d("donates = [$donates]")
        }
    }


    fun showDialog(mainActivity: CoreMainActivity) {
        DonateThanksFragment.newInstance(true)
            .show(mainActivity.supportFragmentManager, "DonateThanksFragment")
      /*  if (donates.isEmpty()) {
            Utils.showShortToast(mainActivity, mainActivity.getString(R.string.error_donate_not_loaded_netigen))
            return
        }
        DonateFragment.newInstance(donates)
            .show(mainActivity.supportFragmentManager, "DonateFragment")*/
    }

    fun checkShowCongrats(mainActivity: CoreMainActivity, paymentEvent: PaymentEvent) {
        Timber.d("paymentEvent = [$paymentEvent]")
        if (paymentEvent is PaymentSuccess && matchesProductId(paymentEvent.sku)) {
            DonateThanksFragment.newInstance(isPremium(paymentEvent.sku))
                .show(mainActivity.supportFragmentManager, "DonateThanksFragment")
        }
    }

    data class DonateInfo(val productId: String, val priceText: String, val productIndex: Int)

    val donatesAvailable
        get() = donates.isNotEmpty()

    companion object {
        fun matchesProductId(productId: String) = productId.contains(Regex("\\.donate\\d"))
        fun isPremium(productId: String) = productId.contains(Regex("\\.donate3"))
        private val donates = mutableListOf<DonateInfo>()
    }
}
