package pl.netigen.core.fragment

import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainVM

/**
 * Base [AppCompatDialogFragment] for Api, provides [canCommitFragments], and [ICoreMainVM]
 *
 */
open class NetigenDialogFragment : AppCompatDialogFragment {
    /**
     * Provides access to Api by [ICoreMainVM]
     */
    val viewModel: ICoreMainVM by activityViewModels<CoreMainVM> { (activity as CoreMainActivity).viewModelFactory }

    constructor() : super() {

    }

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
