package pl.netigen.core.fragment

import androidx.fragment.app.activityViewModels
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainVM

open class NetigenVMFragment : NetigenFragment() {
    val viewModel: ICoreMainVM by activityViewModels<CoreMainVM> { (activity as CoreMainActivity).viewModelFactory }
}