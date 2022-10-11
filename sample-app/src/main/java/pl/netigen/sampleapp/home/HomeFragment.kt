package pl.netigen.sampleapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.android.billingclient.api.ProductDetails
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.netigen.core.fragment.NetigenVMFragment
import pl.netigen.gms.payments.PaymentsState
import pl.netigen.sampleapp.R
import timber.log.Timber.Forest.d

class HomeFragment : NetigenVMFragment() {
    var productDetails: List<ProductDetails> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
        testInterstitial.setOnClickListener { coreMainVM.interstitialAd.showIfCanBeShowed { d("testInterstitial.success:$it") } }
        testInterstitialForce.setOnClickListener { coreMainVM.interstitialAd.showIfCanBeShowed(true) { d("testInterstitialForce.success:$it") } }
        testReward.setOnClickListener { coreMainVM.rewardedAd.showRewardedAd { d("testReward.success:$it") } }
        testNoAds.setOnClickListener { coreMainVM.makeNoAdsPayment(this.requireActivity()) }
        testSubs.setOnClickListener {
            val first = productDetails.firstOrNull { it.productId == "pl.netigen.compass.subs_01" } ?: return@setOnClickListener
            coreMainVM.purchaseSubscriptionOffer(this.requireActivity(), first, first.subscriptionOfferDetails?.firstOrNull()?.offerToken ?: "")
        }
        testResetAds.setOnClickListener { coreMainVM.resetAdsPreferences() }

        (coreMainVM.paymentsStateFlow as StateFlow<PaymentsState>)
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { log(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun log(paymentsState: PaymentsState) {
        d("xxx.+purchases = [${paymentsState.purchaseInfoState.purchases}]")
        productDetails = paymentsState.productDetailsInfoState.productDetails
        d("xxx.+productDetails = [$productDetails]")
    }
}
