package pl.netigen.core.fragment

import androidx.fragment.app.viewModels
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreMainVM

open class NetigenVMFragment : NetigenFragment() {
    val viewModel: ICoreMainVM by viewModels<CoreMainVM> { (activity as CoreMainActivity).viewModelFactory }
}