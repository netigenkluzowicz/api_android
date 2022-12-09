package pl.netigen.sampleapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.home_fragment.*
import pl.netigen.core.fragment.NetigenVMFragment
import pl.netigen.extensions.safeNavigate
import pl.netigen.sampleapp.R
import timber.log.Timber.Forest.d

class HomeFragment : NetigenVMFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
        testInterstitial.setOnClickListener { coreMainVM.interstitialAd.showIfCanBeShowed { d("testInterstitial.success:$it") } }
        testInterstitialForce.setOnClickListener { coreMainVM.interstitialAd.showIfCanBeShowed(true) { d("testInterstitialForce.success:$it") } }
        testReward.setOnClickListener { coreMainVM.rewardedAd.showRewardedAd { d("testReward.success:$it") } }
        testNoAds.setOnClickListener { coreMainVM.makeNoAdsPayment(this.requireActivity()) }
        testSubs.setOnClickListener { coreMainVM.makePurchase(this.requireActivity(), "pl.netigen.compass.subs_01") }
        testResetAds.setOnClickListener { coreMainVM.resetAdsPreferences() }
        testSurvey.setOnClickListener { safeNavigate(R.id.action_homeFragment_to_surveyFragment) }
    }
}
