package pl.netigen.core.splash

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.activityViewModels
import pl.netigen.core.fragment.NetigenFragment
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.gdpr.ICoreSplashFragment
import pl.netigen.coreapi.splash.ISplashVM
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.observe
import timber.log.Timber.Forest.d

abstract class CoreSplashFragment : NetigenFragment(), ICoreSplashFragment {
    open val splashVM: ISplashVM by activityViewModels<SplashVM> { coreMainActivity.viewModelFactory }
    private var onFinishedNotCalled: Boolean = false
    private val coreMainActivity
        get() = requireActivity() as CoreMainActivity

    override val isPremium get() = splashVM.isPremium


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
                SplashState.SHOW_GDPR_POP_UP -> onLoading()
                SplashState.LOADING -> onLoading()
                SplashState.FINISHED -> tryCallOnFinished()
            }
        }
    }

    private fun tryCallOnFinished() {
        if (canCommitFragments) {
            onFinished()
        } else {
            onFinishedNotCalled = true
        }
    }

    private fun onUninitialized() {
        d("()")
        splashVM.start()
    }

    @CallSuper
    open fun onLoading() {
        d("()")
        coreMainActivity.onSplashOpened()
    }

    @CallSuper
    open fun onFinished() {
        d("()")
        coreMainActivity.onSplashClosed()
    }

    override fun onStart() {
        super.onStart()
        d("()")
        splashVM.start()
    }

    override fun onResume() {
        super.onResume()
        if (onFinishedNotCalled) {
            onFinished()
        }
    }

    override fun onConsentAccepted(personalizedAds: Boolean) {
        d("personalizedAds = [$personalizedAds]")
        splashVM.setPersonalizedAds(personalizedAds)
    }

    override fun clickPay() {
        d("()")
        splashVM.makeNoAdsPayment(requireActivity())
    }
}
