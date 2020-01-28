package pl.netigen.core.splash

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import pl.netigen.core.fragment.NetigenFragment
import pl.netigen.core.gdpr.GDPRDialogFragment
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.splash.ISplashVM
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.observe
import timber.log.Timber.d

abstract class SplashFragment : NetigenFragment(), GDPRDialogFragment.GDPRClickListener {
    val splashVM: ISplashVM by activityViewModels<SplashVM> { coreMainActivity.viewModelFactory }
    private var consentNotShowed: Boolean = false
    private var gdprDialogFragment: GDPRDialogFragment? = null
    private val coreMainActivity
        get() = requireActivity() as CoreMainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        d("called")
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
        splashVM.start()
        coreMainActivity.onSplashOpened()
    }

    private fun tryShowGdprPopup() {
        d("called")
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
        gdprDialogFragment = fragment
        bindGdprFragment(fragment)
    }

    private fun bindGdprFragment(fragment: GDPRDialogFragment) {
        fragment.setIsPayOptions(splashVM.isNoAdsAvailable)
        fragment.bindGDPRListener(this)
    }

    private fun showGdprPopup(it: FragmentActivity) {
        val newInstance = GDPRDialogFragment.newInstance()
        gdprDialogFragment = newInstance
        newInstance.show(
            it.supportFragmentManager.beginTransaction().addToBackStack(null),
            GDPR_POP_UP_TAG
        )
        bindGdprFragment(newInstance)
    }

    @CallSuper
    open fun onLoading() {
        gdprDialogFragment?.dismiss()
        coreMainActivity.onSplashOpened()
    }

    @CallSuper
    open fun onFinished() {
        gdprDialogFragment?.dismiss()
        coreMainActivity.onSplashClosed()
    }

    override fun onStart() {
        super.onStart()
        splashVM.start()
    }

    override fun clickYes() {
        splashVM.setPersonalizedAds(true)
    }

    override fun clickPay() {
        splashVM.makeNoAdsPayment(requireActivity())
    }

    override fun clickAcceptPolicy() {
        splashVM.setPersonalizedAds(false)
    }

    companion object {
        private const val GDPR_POP_UP_TAG = "GDPR_POP_UP"
    }
}