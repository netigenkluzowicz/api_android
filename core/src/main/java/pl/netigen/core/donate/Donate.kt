package pl.netigen.core.donate

import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.utils.Utils
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.coreapi.payments.model.PaymentSuccess
import timber.log.Timber

class Donate {
    private val donates = mutableListOf<DonateInfo>()

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
        if (donates.isEmpty()) {
            Utils.showShortToast(mainActivity, "Error - payments info unavailable")
            return
        }
        DonateFragment.newInstance(donates)
            .show(mainActivity.supportFragmentManager, "DonateFragment")
    }

    fun checkShowCongrats(mainActivity: CoreMainActivity, paymentEvent: PaymentEvent) {
        Timber.d("paymentEvent = [$paymentEvent]")
        if (paymentEvent is PaymentSuccess && matchesProductId(paymentEvent.sku)) {
            DonateThanksFragment()
                .show(mainActivity.supportFragmentManager, "DonateFragment")
        }
    }

    data class DonateInfo(val productId: String, val priceText: String, val productIndex: Int)

    val donatesAvailable
        get() = donates.isNotEmpty()

    companion object {
        fun matchesProductId(productId: String) = productId.contains(Regex("\\.donate\\d"))
    }
}
