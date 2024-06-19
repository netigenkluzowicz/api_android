package pl.netigen.sampleapp.home

import android.content.Context.WINDOW_SERVICE
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import pl.netigen.core.fragment.NetigenVMFragment
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.extensions.safeNavigate
import pl.netigen.sampleapp.databinding.HomeFragmentBinding
import timber.log.Timber.Forest.d


class HomeFragment : NetigenVMFragment() {


    lateinit var binding : HomeFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
       binding.testInterstitial.setOnClickListener { coreMainVM.interstitialAd.showIfCanBeShowed { d("testInterstitial.success:$it") } }
       binding.testInterstitialForce.setOnClickListener { coreMainVM.interstitialAd.showIfCanBeShowed(true) { d("testInterstitialForce.success:$it") } }
       binding.testReward.setOnClickListener { coreMainVM.rewardedAd.showRewardedAd { d("testReward.success:$it") } }
       val activity = requireActivity()
       binding.testNoAds.setOnClickListener { coreMainVM.makeNoAdsPayment(activity) }
       binding.testSubs.setOnClickListener { coreMainVM.makePurchase(activity, activity.packageName + ".subs_01") }
       binding.testResetAds.setOnClickListener { coreMainVM.resetAdsPreferences() }
       binding.testSurvey.setOnClickListener { safeNavigate(pl.netigen.sampleapp.R.id.action_homeFragment_to_surveyFragment) }
       binding.newrate.setOnClickListener { (requireActivity() as CoreMainActivity).rateUs.openOurRateDialog() }
       binding.rotate.setOnClickListener {
            val display = (activity.getSystemService(WINDOW_SERVICE) as WindowManager?)!!.defaultDisplay

            val orientation: Int = display.rotation
            coreMainVM.interstitialAd.showIfCanBeShowed(true) {
                when (orientation) {
                    Surface.ROTATION_0, Surface.ROTATION_180 -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    Surface.ROTATION_90, Surface.ROTATION_270 -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
            }
        }
        binding.donate.setOnClickListener {
            (requireActivity() as CoreMainActivity).showDonate()
        }
    }

}
