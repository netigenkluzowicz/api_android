package pl.netigen.core.rateus

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_fragment_rate_us_netigen_api.*
import pl.netigen.core.R
import pl.netigen.core.utils.BaseDialogFragment
import pl.netigen.coreapi.rateus.IRateUs
import pl.netigen.extensions.setTint

/**
 * [BaseDialogFragment] used for show users "Rate us" dialog, see [IRateUs]
 *
 */
class RateFragment : BaseDialogFragment() {

    private var onClickYes: (() -> Unit)? = null
    private var onClickNo: (() -> Unit)? = null
    private var onClickLater: (() -> Unit)? = null

    companion object {
        fun newInstance(onClickYes: () -> Unit, onClickNo: () -> Unit, onClickLater: () -> Unit): RateFragment {
            val fragment = RateFragment()
            fragment.onClickYes = onClickYes
            fragment.onClickNo = onClickNo
            fragment.onClickLater = onClickLater
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_rate_us_netigen_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonsBackgroundTints()
        setClickListeners()
    }

    private fun setButtonsBackgroundTints() {
        context?.let {
            rateUsFragmentYesTextView.background.setTint(it, R.color.dialog_accent, PorterDuff.Mode.MULTIPLY)
            rateUsFragmentLaterTextView.background.setTint(it, R.color.dialog_neutral_button_bg, PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun setClickListeners() {
        setNeutralButtonListener()
        setPositiveButtonListener()
        setNegativeButtonListener()
    }

    private fun setNeutralButtonListener() {
        rateUsFragmentLaterTextView.setOnClickListener {
            onClickLater?.let { it1 -> it1() }
            dismissAllowingStateLoss()
        }
    }

    private fun setPositiveButtonListener() {
        rateUsFragmentYesTextView.setOnClickListener {
            onClickYes?.let { it1 -> it1() }
            dismissAllowingStateLoss()
        }
    }

    private fun setNegativeButtonListener() {
        closeRateUs.setOnClickListener {
            onClickNo?.let { it1 -> it1() }
            dismissAllowingStateLoss()
        }
    }
}
