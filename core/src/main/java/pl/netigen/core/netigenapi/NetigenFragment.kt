package pl.netigen.core.netigenapi

import android.os.Bundle
import androidx.fragment.app.Fragment

open class NetigenFragment : Fragment() {
    
    var canCommitFragments = false

    override fun onResume() {
        super.onResume()
        canCommitFragments = true
    }

    override fun onPause() {
        super.onPause()
        canCommitFragments = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        canCommitFragments = false
    }
}
