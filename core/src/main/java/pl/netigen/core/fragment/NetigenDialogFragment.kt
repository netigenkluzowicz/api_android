package pl.netigen.core.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

open class NetigenDialogFragment : AppCompatDialogFragment() {

    var canCommitFragments = false
        private set

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
