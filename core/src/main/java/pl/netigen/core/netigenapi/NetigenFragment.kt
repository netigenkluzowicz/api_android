package pl.netigen.core.netigenapi

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

abstract class NetigenFragment : Fragment() {

    private var netigenMainActivity: NetigenMainActivity<NetigenViewModel>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            netigenMainActivity = context as NetigenMainActivity<NetigenViewModel>
        }
    }
}