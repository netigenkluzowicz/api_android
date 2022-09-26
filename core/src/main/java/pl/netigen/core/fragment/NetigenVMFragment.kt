package pl.netigen.core.fragment

import androidx.fragment.app.activityViewModels
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainVM

/**
 * Base fragment for Api, provides [canCommitFragments], and [ICoreMainVM]
 *
 */
open class NetigenVMFragment : NetigenFragment() {
    /**
     * Provides access to Api by [ICoreMainVM]
     */
    val coreMainVM: ICoreMainVM by activityViewModels<CoreMainVM> { (activity as CoreMainActivity).viewModelFactory }
}
