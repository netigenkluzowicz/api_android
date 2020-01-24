package com.netigen.sampleapp.feature.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.safeNavigate
import com.netigen.sampleapp.R
import com.netigen.sampleapp.feature.home.SampleMainActivity
import pl.netigen.core.splash.SplashFragment
import timber.log.Timber


class SampleSplashFragment : SplashFragment() {
    override val viewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (requireActivity() as SampleMainActivity).splashVM as T
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.splash_fragment, container, false)

    override fun onFinished() {
        super.onFinished()
        Timber.d("called")
        showHomeFragment()
    }

    private fun showHomeFragment() {
        findNavController().safeNavigate(
            R.id.action_splashFragment_to_homeFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, true)
                .build()
        )
    }
}