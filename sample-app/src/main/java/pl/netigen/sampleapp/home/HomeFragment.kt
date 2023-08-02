package pl.netigen.sampleapp.home

import android.content.Context.WINDOW_SERVICE
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.home_fragment.rotate
import kotlinx.android.synthetic.main.home_fragment.testInterstitial
import kotlinx.android.synthetic.main.home_fragment.testInterstitialForce
import kotlinx.android.synthetic.main.home_fragment.testNoAds
import kotlinx.android.synthetic.main.home_fragment.testResetAds
import kotlinx.android.synthetic.main.home_fragment.testReward
import kotlinx.android.synthetic.main.home_fragment.testSubs
import kotlinx.android.synthetic.main.home_fragment.testSurvey
import pl.netigen.core.fragment.NetigenVMFragment
import pl.netigen.extensions.safeNavigate
import timber.log.Timber.Forest.d


class HomeFragment : NetigenVMFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(pl.netigen.sampleapp.R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
        testInterstitial.setOnClickListener { coreMainVM.interstitialAd.showIfCanBeShowed { d("testInterstitial.success:$it") } }
        testInterstitialForce.setOnClickListener { coreMainVM.interstitialAd.showIfCanBeShowed(true) { d("testInterstitialForce.success:$it") } }
        testReward.setOnClickListener { coreMainVM.rewardedAd.showRewardedAd { d("testReward.success:$it") } }
        val activity = requireActivity()
        testNoAds.setOnClickListener { coreMainVM.makeNoAdsPayment(activity) }
        testSubs.setOnClickListener { coreMainVM.makePurchase(activity, "pl.netigen.compass.subs_01") }
        testResetAds.setOnClickListener { coreMainVM.resetAdsPreferences() }
        testSurvey.setOnClickListener { safeNavigate(pl.netigen.sampleapp.R.id.action_homeFragment_to_surveyFragment) }
        rotate.setOnClickListener {
            val display = (activity.getSystemService(WINDOW_SERVICE) as WindowManager?)!!.defaultDisplay

            val orientation: Int = display.rotation
            coreMainVM.interstitialAd.showIfCanBeShowed(true) {
                when (orientation) {
                    Surface.ROTATION_0, Surface.ROTATION_180 -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    Surface.ROTATION_90, Surface.ROTATION_270 -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
            }
        }
    }

}
