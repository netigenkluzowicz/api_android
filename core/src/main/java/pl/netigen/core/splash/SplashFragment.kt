package pl.netigen.core.splash

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.netigen.core.fragment.NetigenDialogFragment
import pl.netigen.core.gdpr.GDPRDialogFragment
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.observe
import pl.netigen.extensions.setDialogSizeAsMatchParent

private const val GDPR_POP_UP_TAG = "GDPR_POP_UP"

@ExperimentalCoroutinesApi
abstract class SplashFragment : NetigenDialogFragment(), GDPRDialogFragment.GDPRClickListener {
    private var consentNotShowed: Boolean = false
    abstract val viewModel: SplashVM
    private var gdprDialogFragment: GDPRDialogFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.splashState.observe(viewLifecycleOwner) {
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
        }
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
        dismiss()
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        setDialogSizeAsMatchParent()
        viewModel.onStart()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, 0)
    }

    @CallSuper
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun clickYes() {
        viewModel.setPersonalizedAds(true)
    }

    override fun clickPay() {
        activity?.let {
            viewModel.makeNoAdsPayment(it)
        }
    }

    override fun clickAcceptPolicy() {
        viewModel.setPersonalizedAds(false)
    }
}