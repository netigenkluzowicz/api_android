package pl.netigen.core.rateus

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_rate_us_netigen_api.*
import pl.netigen.core.R
import pl.netigen.core.utils.Const.Companion.MARGIN_TOP
import pl.netigen.core.utils.Const.Companion.SCREEN_HEIGHT_IN_DP
import pl.netigen.extensions.setTint
import pl.netigen.extensions.toPx

class RateFragment(

    private val onClickYes: () -> Unit,
    private val onClickNo: () -> Unit,
    private val onClickLater: () -> Unit

) : AppCompatDialogFragment() {

    companion object {
        fun newInstance(onClickYes: () -> Unit, onClickNo: () -> Unit, onClickLater: () -> Unit): RateFragment {
            return RateFragment(onClickYes, onClickNo, onClickLater)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        manageDialogView()
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

    private fun manageDialogView() {
        dialog?.let {
            val window = it.window
            it.setCancelable(true)
            it.setCanceledOnTouchOutside(true)

            if (window != null) {
                window.requestFeature(Window.FEATURE_NO_TITLE)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                manageDialogMarginForSmallScreens(window)
            }
        }
    }

    private fun manageDialogMarginForSmallScreens(window: Window) {
        val resources = context?.resources

        if (resources != null) {
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> window.setLayout(410.toPx(), 280.toPx())
                else -> window.setLayout(280.toPx(), ViewGroup.LayoutParams.WRAP_CONTENT)
            }

            val displayMetrics = resources.displayMetrics
            val currentDeviceHeight = displayMetrics.heightPixels / displayMetrics.density
            if (currentDeviceHeight < SCREEN_HEIGHT_IN_DP) {
                val params = window.attributes
                params.y = MARGIN_TOP
                window.attributes = params
                window.setGravity(Gravity.TOP or Gravity.CENTER)
                window.setLayout(260.toPx(), 380.toPx())
            }
        }
    }

}