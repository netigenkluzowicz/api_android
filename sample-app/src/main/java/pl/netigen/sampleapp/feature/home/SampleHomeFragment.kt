package pl.netigen.sampleapp.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.netigen.sampleapp.R
import kotlinx.android.synthetic.main.home_fragment.*
import pl.netigen.core.fragment.NetigenVMFragment
import timber.log.Timber.d

class SampleHomeFragment : NetigenVMFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
        testInterstitial.setOnClickListener { viewModel.interstitialAd.showInterstitialAd { d("testInterstitial.success:$it") } }
        testInterstitialForce.setOnClickListener { viewModel.interstitialAd.showInterstitialAd(true) { d("testInterstitialForce.success:$it") } }
        testReward.setOnClickListener { viewModel.rewardedAd.showRewardedAd { d("testReward.success:$it") } }
    }
}