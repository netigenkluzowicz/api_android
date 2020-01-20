package com.netigen.sampleapp.feature.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.netigen.sampleapp.R
import com.netigen.sampleapp.feature.home.SampleMainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.netigen.core.splash.SplashFragment
import pl.netigen.coreapi.splash.SplashVM
import timber.log.Timber

@ExperimentalCoroutinesApi
class SampleSplashFragment : SplashFragment() {
    override val viewModel: SplashVM by lazy { (requireActivity() as SampleMainActivity).splashVM }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onFinished() {
        super.onFinished()
        Timber.d("called")
    }
}