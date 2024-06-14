package pl.netigen.sampleapp.home

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.extensions.observe
import pl.netigen.extensions.safeNavigate
import pl.netigen.sampleapp.R
import timber.log.Timber

class MainActivity : CoreMainActivity() {
    override val viewModelFactory: CoreViewModelsFactory
        get() = ViewModelFactory(this)

    override fun hideAds() {
        findViewById<View>(R.id.adsLayout).visibility = GONE
    }

    override fun showAds() {
        findViewById<View>(R.id.adsLayout).visibility = VISIBLE
    }

    override fun openSurveyFragment() = findNavController(R.id.layoutHomeContainer).safeNavigate(R.id.action_homeFragment_to_surveyFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)
        Timber.d("activity %s", this)
        coreMainVM.noAdsActive.asLiveData().observe(this) {
            Timber.d("gms_noAds:$it")

        }
    }
}
