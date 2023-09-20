package pl.netigen.core.donate

import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import timber.log.Timber

class Donate {
    private val donates = mutableListOf<DonateInfo>()

    fun updateDetails(skuDetails: List<NetigenSkuDetails>) {
        if (skuDetails.isNotEmpty()) {
            donates.clear()
            for ((productId, _, _, _, priceAmountMicros, priceCurrencyCode, _, _, _) in skuDetails) {

                val priceText = String.format("%.2f", priceAmountMicros?.div(1000000.0) ?: 0).replace(",", ".") + " " + priceCurrencyCode
                if (productId.contains(Regex("\\.donate\\d"))) {
                    donates.add(DonateInfo(productId, priceText))
                }
            }
            Timber.d("donates = [$donates]")
        }
    }

    fun showDialog(coreMainActivity: CoreMainActivity) = DonateFragment.newInstance()
        .show(coreMainActivity.supportFragmentManager, "DonateFragment")

    data class DonateInfo(val productId: String, val priceText: String)

    val donatesAvailable
        get() = donates.isNotEmpty()
}
