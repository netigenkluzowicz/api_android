package pl.netigen.sampleapp.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import pl.netigen.core.splash.CoreSplashFragment
import pl.netigen.extensions.safeNavigate
import pl.netigen.sampleapp.R
import timber.log.Timber

class SplashFragment : CoreSplashFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.splash_fragment, container, false)

    override fun onFinished() {
        super.onFinished()
        Timber.d("()")
        showHomeFragment()
    }

    private fun showHomeFragment() {
        Timber.d("()")
        findNavController().safeNavigate(
            R.id.action_splashFragment_to_homeFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, true)
                .build(),
        )
    }
}
