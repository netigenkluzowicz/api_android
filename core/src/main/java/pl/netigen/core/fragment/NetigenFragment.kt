package pl.netigen.core.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Base fragment, provides [canCommitFragments]
 *
 */
open class NetigenFragment : Fragment() {
    /**
     * Indicates if we can safe perform Fragment transaction
     * as [commit()][FragmentTransaction.commit] or [popBackStack()][FragmentManager.popBackStack] and others
     * otherwise it will result with
     * [IllegalStateException][java.lang.IllegalStateException] crash
     *
     * see: [FragmentManager.isStateSaved]
     *
     * see: [stackoverflow](https://stackoverflow.com/a/44064149/3442734)
     */
    val canCommitFragments
        get() = !childFragmentManager.isStateSaved
}
