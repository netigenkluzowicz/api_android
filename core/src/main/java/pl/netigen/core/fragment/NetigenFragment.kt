package pl.netigen.core.fragment

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

open class NetigenFragment : Fragment() {
    var canCommitFragments = false
        private set

    @CallSuper
    override fun onResume() {
        super.onResume()
        canCommitFragments = true
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        canCommitFragments = false
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        canCommitFragments = false
    }
}
