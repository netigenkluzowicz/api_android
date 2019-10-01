package pl.netigen.core.netigenapi

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import pl.netigen.gdpr.GDPRDialogFragment
import android.app.Activity
import androidx.lifecycle.Observer
import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import pl.netigen.core.gdpr.ConstRodo

abstract class NetigenSplashFragment<ViewModel : NetigenViewModel> : Fragment(), GDPRDialogFragment.GDPRClickListener {

    open lateinit var viewModel: ViewModel
    lateinit var netigenMainActivity: NetigenMainActivity<NetigenViewModel>
    private var gdprDialogFragment: GDPRDialogFragment? = null
    private lateinit var initAdsHandler: Handler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupParentActivity(context)
        netigenMainActivity.checkIfNoAdsBought()
        viewModel.noAdsLiveData.observe(this, Observer {
            if (it) {
                showHomeFragment()
                gdprDialogFragment?.dialog?.dismiss()
            } else {
                showConsent()
            }
        })
        onNoAdsPaymentNotAvailable()
        netigenMainActivity.hideAds()
    }

    private fun setupParentActivity(context: Context) {
        if (context is Activity) {
            if (context is NetigenMainActivity<*>) {
                netigenMainActivity = context as NetigenMainActivity<NetigenViewModel>
                viewModel = netigenMainActivity.viewModel as ViewModel
            } else {
                throw IllegalStateException("Parent activity should be of type NetigenMainActivity<VM: NetigenViewModel>")
            }
            netigenMainActivity.hideBanner()
        }
    }

    private fun onNoAdsPaymentNotAvailable() {
        if (!viewModel.isNoAdsPaymentAvailable) {
            viewModel.isNoAdsBought = false
            if (viewModel.isDesignedForFamily) {
                showConsent()
            } else {
                clickNo()
                showHomeFragment()
            }
        }
    }

    private fun showConsent() {
        val consentInformation = ConsentInformation.getInstance(context)
        consentInformation.requestConsentInfoUpdate(viewModel.publishersIds, object : ConsentInfoUpdateListener {
            override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                val isInEea = ConsentInformation.getInstance(this@NetigenSplashFragment.context).isRequestLocationInEeaOrUnknown
                ConstRodo.setIsInEea(isInEea)
                if (isInEea && consentInformation.consentStatus == ConsentStatus.UNKNOWN) {
                    initGDPRFragment()
                } else {
                    startAdsSplash()
                }
            }

            override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                startAdsSplash()
            }
        })
    }

    override fun clickNo() {
        ConsentInformation.getInstance(context).consentStatus = ConsentStatus.NON_PERSONALIZED
    }

    abstract fun showHomeFragment()

    private fun initGDPRFragment() {
        gdprDialogFragment = GDPRDialogFragment.newInstance()
        gdprDialogFragment?.setIsPayOptions(viewModel.isNoAdsPaymentAvailable)
        gdprDialogFragment?.show(netigenMainActivity.supportFragmentManager.beginTransaction().addToBackStack(null), "")
        gdprDialogFragment?.bindGDPRListener(this)
    }

    private fun startAdsSplash() {
        if (viewModel.isDesignedForFamily) {
            clickNo()
            showHomeFragment()
        } else {
            if (!::initAdsHandler.isInitialized) {
                initAdsHandler = Handler()
                initAdsHandler.post { this.initAds() }
            }
        }
    }

    internal open fun initAds() {
        netigenMainActivity.initAdsManager()
        netigenMainActivity.adsManager?.launchSplashLoaderOrOpenFragment {
            showHomeFragment()
        }
    }

    override fun clickAcceptPolicy() {
        startAdsSplash()
    }

    override fun onStart() {
        super.onStart()
        netigenMainActivity.adsManager?.splashScreenOnStart()
    }

    override fun onStop() {
        super.onStop()
        netigenMainActivity.adsManager?.splashScreenOnStop()
    }

    override fun onDestroyView() {
        netigenMainActivity.showAds()
        netigenMainActivity.showBanner()
        netigenMainActivity.adsManager?.splashScreenOnDestroy()
        super.onDestroyView()
    }

    fun onNoAdsPaymentProcessingFinished(noAdsBought: Boolean) {
        viewModel.isNoAdsBought = noAdsBought
    }

    override fun clickYes() {
        ConsentInformation.getInstance(context).consentStatus = ConsentStatus.PERSONALIZED
        startAdsSplash()
    }

}