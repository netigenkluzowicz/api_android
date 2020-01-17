package pl.netigen.core.splash

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.netigen.core.gdpr.GDPRDialogFragment
import pl.netigen.core.netigenapi.NetigenFragment
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.coreapi.splash.SplashVM

private const val GDPR_POP_UP_TAG = "GDPR_POP_UP"

@ExperimentalCoroutinesApi
abstract class SplashFragment : NetigenFragment(), GDPRDialogFragment.GDPRClickListener {

    private var consentNotShowed: Boolean = false

    abstract val viewModel: SplashVM
    private var gdprDialogFragment: GDPRDialogFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.splashState.observe(viewLifecycleOwner, Observer {
            when (it) {
                SplashState.UNINITIALIZED -> {
                    onUninitialized()
                }
                SplashState.SHOW_GDPR_CONSENT -> {
                    tryShowGdprPopup()
                }
                SplashState.LOADING -> {
                    onLoading()
                }
                SplashState.FINISHED -> {
                    onFinished()
                }
            }
        })
    }

    private fun onUninitialized() {
        gdprDialogFragment?.dismiss()
        viewModel.onStart()
    }

    private fun tryShowGdprPopup() {
        if (!canCommitFragments) {
            consentNotShowed = true
            return
        }
        activity?.let {
            val fragment =
                it.supportFragmentManager.findFragmentByTag(GDPR_POP_UP_TAG) as GDPRDialogFragment?
            if (fragment != null) {
                onGdprPopupVisible(fragment)
            } else {
                showGdprPopup(it)
            }
        }
    }

    private fun onGdprPopupVisible(fragment: GDPRDialogFragment) {
        gdprDialogFragment = fragment
        bindGdprFragment(fragment)
    }

    private fun bindGdprFragment(fragment: GDPRDialogFragment) {
        fragment.setIsPayOptions(viewModel.isNoAdsAvailable)
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
    }

    @CallSuper
    open fun onFinished() {
        gdprDialogFragment?.dismiss()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun clickYes() {
        viewModel.onGdprDialogResult(true)
    }

    override fun clickPay() {
        activity?.let {
            viewModel.makeNoAdsPayment(it)
        }
    }

    override fun clickAcceptPolicy() {
        viewModel.onGdprDialogResult(false)
    }
}