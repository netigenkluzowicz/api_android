package pl.netigen.sampleapp.home

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.UNSET
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_sample_main.*
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.extensions.observe
import pl.netigen.sampleapp.R
import timber.log.Timber

class MainActivity : CoreMainActivity() {
    override val viewModelFactory: CoreViewModelsFactory
        get() = ViewModelFactory(this)

    override fun hideAds() {
        Timber.d("()")
        val navContainer = layoutHomeContainer.view ?: return
        val layoutParams = navContainer.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = UNSET
        layoutParams.bottomToBottom = PARENT_ID
        navContainer.layoutParams = layoutParams
    }

    override fun showAds() {
        Timber.d("()")
        val navContainer = layoutHomeContainer.view ?: return
        val layoutParams = navContainer.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = adsBorder.id
        layoutParams.bottomToBottom = UNSET
        navContainer.layoutParams = layoutParams
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)
        Timber.d("activity %s", this)
        coreMainVM.noAdsActive.asLiveData().observe(this) {
            Timber.d("gms_noAds:$it")
        }
    }
}
