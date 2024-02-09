package pl.netigen.core.rateus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import pl.netigen.core.R
import pl.netigen.core.utils.AbstractBaseBottomFullDialog
import pl.netigen.core.utils.BaseDialogFragment
import pl.netigen.core.utils.Utils

/**
 * [BaseDialogFragment] used for show users "Ask For Survey" dialog, see [ISurvey]
 *
 */
class RateUsAskGoogleFragment : AbstractBaseBottomFullDialog() {
    private var onClickYes: ((Int) -> Unit)? = null
    private var onClose: (() -> Unit)? = null

    companion object {
        fun newInstance(onClickYes: (Int) -> Unit = {}, onClose: (() -> Unit) = {}): RateUsAskGoogleFragment {
            val fragment = RateUsAskGoogleFragment()
            fragment.onClickYes = onClickYes
            fragment.onClose = onClose
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (onClickYes == null) {
            dismissAllowingStateLoss()
            return
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.new_rate_us_ask_netigen, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        val confirmBtn = requireView().findViewById<TextView>(R.id.confirmButton)
        confirmBtn.setOnClickListener {
            Utils.openMarketLink(requireActivity(), requireActivity().packageName)
            dismissAllowingStateLoss()
        }
        requireView().findViewById<View>(R.id.skip).setOnClickListener {
            dismissAllowingStateLoss()
            onClose?.invoke()
        }
    }
}
