package pl.netigen.core.netigenapi

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.netigen.gdpr.GDPRDialogFragment
import android.app.Activity

abstract class NetigenSplashFragment<ViewModel : NetigenViewModel> : Fragment() {

    lateinit var viewModel: ViewModel
    private var gdprDialogFragment: GDPRDialogFragment? = null
    private var canCommitFragment: Boolean = false
    private lateinit var initAdmobHandler: Handler
    private lateinit var netigenMainActivity: NetigenMainActivity<NetigenViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            netigenMainActivity = context as NetigenMainActivity<NetigenViewModel>
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}