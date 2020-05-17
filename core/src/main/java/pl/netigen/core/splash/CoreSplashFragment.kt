package pl.netigen.core.splash

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import pl.netigen.core.fragment.NetigenFragment
import pl.netigen.core.gdpr.GDPRDialogFragment
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.gdpr.ICoreSplashFragment
import pl.netigen.coreapi.splash.ISplashVM
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.observe
import timber.log.Timber.d

abstract class CoreSplashFragment : NetigenFragment(), ICoreSplashFragment {
    private val splashVM: ISplashVM by activityViewModels<SplashVM> { coreMainActivity.viewModelFactory }
    private var consentNotShowed: Boolean = false
    private var gdprDialogFragment: GDPRDialogFragment? = null
    private val coreMainActivity
        get() = requireActivity() as CoreMainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        d("view = [$view], savedInstanceState = [$savedInstanceState]")
        observe()
    }

    private fun observe() {
        splashVM.splashState.observe(viewLifecycleOwner) {
            d(it.toString())
            when (it) {
                SplashState.UNINITIALIZED -> onUninitialized()
                SplashState.SHOW_GDPR_CONSENT -> tryShowGdprPopup()
                SplashState.LOADING -> onLoading()
                SplashState.FINISHED -> onFinished()
            }
        }
    }

    private fun onUninitialized() {
        d("()")
        splashVM.start()
    }

    private fun tryShowGdprPopup() {
        d("()")
        coreMainActivity.onSplashOpened()
        if (!canCommitFragments) {
            consentNotShowed = true
            return
        }
        val fragment =
            requireActivity().supportFragmentManager.findFragmentByTag(GDPR_POP_UP_TAG) as GDPRDialogFragment?
        if (fragment != null) {
            onGdprPopupVisible(fragment)
        } else {
            showGdprPopup(requireActivity())
        }
    }

    private fun onGdprPopupVisible(fragment: GDPRDialogFragment) {
        d("fragment = [$fragment]")
        gdprDialogFragment = fragment
        bindGdprFragment(fragment)
    }

    private fun bindGdprFragment(fragment: GDPRDialogFragment) {
        d("fragment = [$fragment]")
        fragment.setIsPayOptions(splashVM.isNoAdsAvailable)
        fragment.bindGDPRListener(this)
    }

    private fun showGdprPopup(fragmentActivity: FragmentActivity) {
        d("it = [$fragmentActivity]")
        val newInstance = GDPRDialogFragment.newInstance()
        gdprDialogFragment = newInstance
        newInstance.show(
            fragmentActivity.supportFragmentManager.beginTransaction().addToBackStack(null),
            GDPR_POP_UP_TAG
        )
        bindGdprFragment(newInstance)
    }

    @CallSuper
    open fun onLoading() {
        d("()")
        gdprDialogFragment?.dismissAllowingStateLoss()
        coreMainActivity.onSplashOpened()
    }

    @CallSuper
    open fun onFinished() {
        d("()")
        gdprDialogFragment?.dismissAllowingStateLoss()
        coreMainActivity.onSplashClosed()
    }

    override fun onStart() {
        super.onStart()
        d("()")
        splashVM.start()
    }

    override fun onResume() {
        super.onResume()
        if (consentNotShowed) {
            if (canCommitFragments) {
                tryShowGdprPopup()
            } else {
                onFinished()
            }
        }
    }

    override fun onConsentAccepted(personalizedAds: Boolean)  {
        d("personalizedAds = [$personalizedAds]")
        splashVM.setPersonalizedAds(personalizedAds)
    }

    override fun clickPay() {
        d("()")
        splashVM.makeNoAdsPayment(requireActivity())
    }

    companion object {
        private const val GDPR_POP_UP_TAG = "GDPR_POP_UP"
    }
}