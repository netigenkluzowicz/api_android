package pl.netigen.core.rateus

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.dialog_fragment_rate_us_netigen_api.*
import pl.netigen.core.R
import pl.netigen.core.utils.BaseDialogFragment
import pl.netigen.extensions.setTint

class RateFragment(

    private val onClickYes: () -> Unit,
    private val onClickNo: () -> Unit,
    private val onClickLater: () -> Unit

) : BaseDialogFragment(R.layout.dialog_fragment_rate_us_netigen_api) {

    companion object {
        fun newInstance(onClickYes: () -> Unit, onClickNo: () -> Unit, onClickLater: () -> Unit): RateFragment {
            return RateFragment(onClickYes, onClickNo, onClickLater)
        }
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
            onClickLater()
            dismiss()
        }
    }

    private fun setPositiveButtonListener() {
        rateUsFragmentYesTextView.setOnClickListener {
            onClickYes()
            dismiss()
        }
    }

    private fun setNegativeButtonListener() {
        closeRateUs.setOnClickListener {
            onClickNo()
            dismiss()
        }
    }
}