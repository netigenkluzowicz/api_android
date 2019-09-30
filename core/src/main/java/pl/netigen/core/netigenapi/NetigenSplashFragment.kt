package pl.netigen.core.netigenapi

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import pl.netigen.gdpr.GDPRDialogFragment
import android.app.Activity
import android.util.Log
import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import pl.netigen.core.gdpr.ConstRodo

abstract class NetigenSplashFragment<ViewModel : NetigenViewModel> : Fragment() {

    open lateinit var viewModel: ViewModel
    lateinit var netigenMainActivity: NetigenMainActivity<NetigenViewModel>
    var consentFinished = false
    var canCommitFragment = false
    private var gdprDialogFragment: GDPRDialogFragment? = null
    private lateinit var initAdsHandler: Handler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("NetigenSplashFragment", " onAttach: noAdsPaymentAvailable ${viewModel.isNoAdsPaymentAvailable} designForFamily ${viewModel.isDesignedForFamily}");
        if (context is Activity) {
            netigenMainActivity = context as NetigenMainActivity<NetigenViewModel>
            netigenMainActivity.hideBanner()
        }
        //todo show consent if needed, otherwise try to show interstitial will a callback to run homeFragment on interstitalClosed
        //if splash fragment is opened it means that noAds haven't been bought
        if (true) {
//        if (!viewModel.isNoAdsPaymentAvailable) {
            viewModel.isNoAdsBought = false
            if (viewModel.isDesignedForFamily){
                Log.d("NetigenSplashFragment", " onAttach: will showConsent")
                showConsent()
            }
            else {
                Log.d("NetigenSplashFragment", " onAttach: designed for families")
                clickNo()
                showHomeFragment()
            }
        }
        netigenMainActivity.hideAds()
    }

    override fun onDetach() {
        Log.d("NetigenSplashFragment", " onDetach: ")
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("NetigenSplashFragment", " onCreate: ")
    }

    private fun showConsent() {
        Log.d("NetigenSplashFragment", " showConsent: ")
        val consentInformation = ConsentInformation.getInstance(context)
        consentInformation.requestConsentInfoUpdate(viewModel.publishersIds, object : ConsentInfoUpdateListener {
            override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                val isInEea = ConsentInformation.getInstance(this@NetigenSplashFragment.context).isRequestLocationInEeaOrUnknown
                ConstRodo.setIsInEea(isInEea)
                if (isInEea && consentInformation.consentStatus == ConsentStatus.UNKNOWN) {
                    initGDPRFragment()
                    Log.d("NetigenSplashFragment", " onConsentInfoUpdated: will init gdpr")
                } else {
                    Log.d("NetigenSplashFragment", " onConsentInfoUpdated: will start ads")
                    consentFinished = true
                    startAdsSplash()
                }
            }

            override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                startAdsSplash()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        Log.d("NetigenSplashFragment", " onStop: ")
        canCommitFragment = false
        netigenMainActivity.adsManager?.splashScreenOnStop()
    }

    override fun onStart() {
        super.onStart()
        Log.d("NetigenSplashFragment", " onStart: ")
        canCommitFragment = true
        netigenMainActivity.adsManager?.splashScreenOnStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("NetigenSplashFragment", " onDestroy: ")
        netigenMainActivity.adsManager?.splashScreenOnDestroy()
    }

    private fun initGDPRFragment() {
        Log.d("NetigenSplashFragment", " initRodoFragment: ")
        if (canCommitFragment) {
            gdprDialogFragment = GDPRDialogFragment.newInstance()
            gdprDialogFragment!!.setIsPayOptions(viewModel.isNoAdsPaymentAvailable)
            gdprDialogFragment!!.show(netigenMainActivity.supportFragmentManager.beginTransaction().addToBackStack(null), "")
        }
    }

    private fun startAdsSplash() {
        Log.d("NetigenSplashFragment", " startAdsSplash: ")
        if (viewModel.isDesignedForFamily) {
            if (!::initAdsHandler.isInitialized) {
                initAdsHandler = Handler()
                initAdsHandler.post { this.initAds() }
            }
        } else {
            clickNo()
            showHomeFragment()
        }
    }

    internal open fun initAds() {
        netigenMainActivity.initAdsManager()
    }

    private fun showHomeFragment() {
        netigenMainActivity.showHomeFragment()
    }

    fun clickYes() {
        ConsentInformation.getInstance(context).consentStatus = ConsentStatus.PERSONALIZED
        closeGDPRFragment()
        startAdsSplash()
        consentFinished = true
    }

    private fun closeGDPRFragment() {
        gdprDialogFragment?.dialog?.dismiss()
        gdprDialogFragment = null
    }

    fun clickNo() {
        ConsentInformation.getInstance(context).consentStatus = ConsentStatus.NON_PERSONALIZED
    }

    fun clickAcceptPolicy() {
        closeGDPRFragment()
        startAdsSplash()
        consentFinished = true
    }

    fun onNoAdsPaymentProcessingFinished(noAdsBought: Boolean) {
        closeGDPRFragment()
        viewModel.isNoAdsBought = true
        if (noAdsBought)
            showHomeFragment()
        else
            showConsent()
    }
}