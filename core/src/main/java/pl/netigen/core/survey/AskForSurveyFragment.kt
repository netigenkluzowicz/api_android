package pl.netigen.core.survey

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_fragment_rate_us_netigen_api.*
import kotlinx.android.synthetic.main.dialog_fragment_survey_netigen_api.*
import pl.netigen.core.R
import pl.netigen.core.utils.BaseDialogFragment
import pl.netigen.extensions.setTint

/**
 * [BaseDialogFragment] used for show users "Ask For Survey" dialog, see [ISurvey]
 *
 */
class AskForSurveyFragment : BaseDialogFragment() {

    private var onClickYes: (() -> Unit)? = null
    private var onClickNo: (() -> Unit)? = null

    companion object {
        fun newInstance(onClickYes: () -> Unit, onClickNo: () -> Unit): AskForSurveyFragment {
            val fragment = AskForSurveyFragment()
            fragment.onClickYes = onClickYes
            fragment.onClickNo = onClickNo
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
        return inflater.inflate(R.layout.dialog_fragment_survey_netigen_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonsBackgroundTints()
        setClickListeners()
    }

    private fun setButtonsBackgroundTints() {
        context?.let {
            surveyFragmentYesTextView.background.setTint(it, R.color.dialog_accent, PorterDuff.Mode.MULTIPLY)
            surveyFragmentLaterTextView.background.setTint(it, R.color.dialog_neutral_button_bg, PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun setClickListeners() {
        setNegativeButtonListener()
        setPositiveButtonListener()
    }


    private fun setPositiveButtonListener() {
        surveyFragmentYesTextView.setOnClickListener {
            onClickYes?.let { it() }
            dismissAllowingStateLoss()
        }
    }

    private fun setNegativeButtonListener() {
        closeSurvey.setOnClickListener {
            onClickNo?.let { it() }
            dismissAllowingStateLoss()
        }
    }
}