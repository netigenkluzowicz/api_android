package pl.netigen.core.netigenapi

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentStatus
import pl.netigen.core.gdpr.ConstGDPR
import pl.netigen.core.gdpr.GDPRDialogFragment

abstract class NetigenSplashFragment<ViewModel : NetigenViewModel> : NetigenFragment(), GDPRDialogFragment.GDPRClickListener {
    var shouldShowHomeFragmentOnResume = false
        private set
    private var consentNotShowed: Boolean = false
    open lateinit var viewModel: ViewModel
    lateinit var netigenMainActivity: NetigenMainActivity<NetigenViewModel>
    private var gdprDialogFragment: GDPRDialogFragment? = null
    private lateinit var initAdsHandler: Handler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupParentActivity(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startNoAdsLogic()
        netigenMainActivity.hideBanner()
        netigenMainActivity.hideAds()
    }

    private fun setupParentActivity(context: Context) {
        if (context is NetigenMainActivity<*>) {
            netigenMainActivity = context as NetigenMainActivity<NetigenViewModel>
            viewModel = netigenMainActivity.viewModel as ViewModel
        } else {
            throw IllegalStateException("Parent activity should be of type NetigenMainActivity<VM: NetigenViewModel>")
        }
    }

    private fun startNoAdsLogic() {
        if (viewModel.isNoAdsPaymentAvailable) {
            netigenMainActivity.checkIfNoAdsBought()
            observeNoAds()
        } else {
            onNoAdsPaymentNotAvailable()
        }
    }

    override fun onResume() {
        super.onResume()
        if (consentNotShowed) {
            onConsentInfoUpdated(netigenMainActivity)
            consentNotShowed = false
            return
        }
        if (shouldShowHomeFragmentOnResume) {
            showHomeFragment()
            shouldShowHomeFragmentOnResume = false
        }
    }

    private fun observeNoAds() {
        viewModel.noAdsLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                onCreateWithoutAds()
            } else {
                onCreateWithAds()
            }
        })
    }

    private fun onCreateWithoutAds() {
        if (canCommitFragments) gdprDialogFragment?.dialog?.dismiss()
        showHomeFragmentOrSetFlag()
    }

    abstract fun showHomeFragment()

    private fun onCreateWithAds() {
        if (viewModel.isDesignedForFamily) {
            onDesignedForFamily()
        } else {
            showConsent()
        }
    }

    private fun onNoAdsPaymentNotAvailable() {
        viewModel.isNoAdsBought = false
        onCreateWithAds()
    }

    private fun onDesignedForFamily() {
        clickNo()
        initAds()
        showHomeFragmentOrSetFlag()
    }

    private fun showConsent() {
        gdprDialogFragment?.let {
            if (it.isAdded) return
        }

        netigenMainActivity.consentInformation.requestConsentInfoUpdate(viewModel.publishersIds, object : ConsentInfoUpdateListener {
            override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                if (canCommitFragments) {
                    onConsentInfoUpdated(netigenMainActivity)
                } else {
                    consentNotShowed = true
                }
            }

            override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                startAdsSplash()
            }
        })
    }

    private fun onConsentInfoUpdated(it: NetigenMainActivity<NetigenViewModel>) {
        ConstGDPR.isInEea = it.consentInformation.isRequestLocationInEeaOrUnknown
        if (ConstGDPR.isInEea && it.consentInformation.consentStatus == ConsentStatus.UNKNOWN) {
            initGDPRFragment()
        } else {
            startAdsSplash()
        }
    }

    override fun clickNo() {
        netigenMainActivity.consentInformation.consentStatus = ConsentStatus.NON_PERSONALIZED
    }

    private fun initGDPRFragment() {
        if (!canCommitFragments) {
            consentNotShowed = true
            return
        }
        val fragment = netigenMainActivity.supportFragmentManager.findFragmentByTag(GDPR_POP_UP_TAG) as GDPRDialogFragment?
        if (fragment != null) {
            gdprDialogFragment = fragment
            bindGdprFragment(fragment)
        } else {
            val newInstance = GDPRDialogFragment.newInstance()
            gdprDialogFragment = newInstance
            newInstance.show(netigenMainActivity.supportFragmentManager.beginTransaction().addToBackStack(null), GDPR_POP_UP_TAG)
            bindGdprFragment(newInstance)
        }
    }

    private fun bindGdprFragment(fragment: GDPRDialogFragment) {
        fragment.setIsPayOptions(viewModel.isNoAdsPaymentAvailable)
        fragment.bindGDPRListener(this)
    }

    private fun startAdsSplash() {
        if (viewModel.isDesignedForFamily) {
            clickNo()
            showHomeFragmentOrSetFlag()
        } else {
            if (!::initAdsHandler.isInitialized) {
                initAdsHandler = Handler()
                initAdsHandler.post { this.initAds() }
            }
        }
    }

    internal open fun initAds() {
        netigenMainActivity.initAdsManager()
        val adsManager = netigenMainActivity.admobAds
        if (adsManager != null) {
            adsManager.showInterstitialAd { showHomeFragmentOrSetFlag() }
        } else {
            showHomeFragmentOrSetFlag()
        }
    }

    private fun showHomeFragmentOrSetFlag() {
        if (canCommitFragments) {
            showHomeFragment()
        } else {
            shouldShowHomeFragmentOnResume = true
        }
    }

    override fun clickAcceptPolicy() {
        startAdsSplash()
    }

    override fun onDestroyView() {
        if (!viewModel.isNoAdsBought) {
            netigenMainActivity.showAds()
            netigenMainActivity.showBanner()
        }
        super.onDestroyView()
    }

    override fun clickYes() {
        netigenMainActivity.consentInformation.consentStatus = ConsentStatus.PERSONALIZED
        startAdsSplash()
    }

}

private const val GDPR_POP_UP_TAG = "GDPR_POP_UP"