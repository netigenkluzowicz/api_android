package pl.netigen.gdpr

import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.fragment_blank.*
import pl.netigen.netigenapi.R

class GDPRDialogFragment : AppCompatDialogFragment() {

    var isPayOptions = false

    override fun onStart() {
        super.onStart()
        setDialogSize(0.9, 0.9)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_gdpr, container, false)

        try {
            val icon = activity!!.packageManager.getApplicationIcon(activity!!.packageName)
            iconApplication.setImageDrawable(icon)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {

        fun newInstance(): GDPRDialogFragment {
            val dialogFragment = GDPRDialogFragment()
            return dialogFragment
        }
    }

}