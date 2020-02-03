package pl.netigen.core.fragment

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import timber.log.Timber

open class NetigenFragment : Fragment() {
    var canCommitFragments = false
        private set

    @CallSuper
    override fun onResume() {
        super.onResume()
        Timber.d("()")
        canCommitFragments = true
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        Timber.d("()")
        canCommitFragments = false
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d("()")
        canCommitFragments = false
    }
}
