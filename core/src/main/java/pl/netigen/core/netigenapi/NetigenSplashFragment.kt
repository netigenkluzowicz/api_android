package pl.netigen.core.netigenapi

import android.content.Context
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import pl.netigen.core.gdpr.ConstGDPR
import pl.netigen.gdpr.GDPRDialogFragment

abstract class NetigenSplashFragment<ViewModel : NetigenViewModel> : Fragment(), GDPRDialogFragment.GDPRClickListener {

    open lateinit var viewModel: ViewModel
    var netigenMainActivity: NetigenMainActivity<NetigenViewModel>? = null
    private var gdprDialogFragment: GDPRDialogFragment? = null
    private lateinit var initAdsHandler: Handler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupParentActivity(context)
        startNoAdsLogic()
        netigenMainActivity?.hideAds()
    }

    private fun setupParentActivity(context: Context) {
        if (context is NetigenMainActivity<*>) {
            netigenMainActivity = context as NetigenMainActivity<NetigenViewModel>
            viewModel = netigenMainActivity?.viewModel as ViewModel
        } else {
            throw IllegalStateException("Parent activity should be of type NetigenMainActivity<VM: NetigenViewModel>")
        }
        netigenMainActivity?.hideBanner()
    }

    private fun startNoAdsLogic() {
        if (viewModel.isNoAdsPaymentAvailable) {
            netigenMainActivity?.checkIfNoAdsBought()
            observeNoAds()
        } else {
            onNoAdsPaymentNotAvailable()
        }
    }

    private fun observeNoAds() {
        viewModel.noAdsLiveData.observe(this, Observer {
            if (it) {
                onAttachWithoutAds()
            } else {
                onAttachWithAds()
            }
        })
    }

    private fun onAttachWithoutAds() {
        showHomeFragment()
        gdprDialogFragment?.dialog?.dismiss()
    }

    abstract fun showHomeFragment()

    private fun onAttachWithAds() {
        if (viewModel.isDesignedForFamily) {
            onDesignedForFamily()
        } else {
            showConsent()
        }
    }

    private fun onNoAdsPaymentNotAvailable() {
        viewModel.isNoAdsBought = false
        onAttachWithAds()
    }

    private fun onDesignedForFamily() {
        showHomeFragment()
        clickNo()
        initAds()
    }

    private fun showConsent() {
        netigenMainActivity?.let {
            it.consentInformation.requestConsentInfoUpdate(viewModel.publishersIds, object : ConsentInfoUpdateListener {
                override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                    ConstGDPR.isInEea = viewModel.isInEea
                    if (ConstGDPR.isInEea && it.consentInformation.consentStatus == ConsentStatus.UNKNOWN) {
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
    }

    override fun clickNo() {
        ConsentInformation.getInstance(context).consentStatus = ConsentStatus.NON_PERSONALIZED
    }

    private fun initGDPRFragment() {
        gdprDialogFragment = GDPRDialogFragment.newInstance()
        gdprDialogFragment?.setIsPayOptions(viewModel.isNoAdsPaymentAvailable)
        netigenMainActivity?.let { gdprDialogFragment?.show(it.supportFragmentManager.beginTransaction().addToBackStack(null), "") }
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
        netigenMainActivity?.initAdsManager()
        netigenMainActivity?.adsManager?.launchSplashLoaderOrOpenFragment {
            showHomeFragment()
        }
    }

    override fun clickAcceptPolicy() {
        startAdsSplash()
    }

    override fun onStart() {
        super.onStart()
        netigenMainActivity?.adsManager?.splashScreenOnStart()
    }

    override fun onStop() {
        super.onStop()
        netigenMainActivity?.adsManager?.splashScreenOnStop()
    }

    override fun onDestroyView() {
        if (!viewModel.isNoAdsBought) {
            netigenMainActivity?.showAds()
            netigenMainActivity?.showBanner()
        }
        netigenMainActivity?.adsManager?.splashScreenOnDestroy()
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