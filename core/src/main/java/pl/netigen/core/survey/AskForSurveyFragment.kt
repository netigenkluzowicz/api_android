package pl.netigen.core.survey

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_fragment_ask_for_survey_netigen_api.*
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
        return inflater.inflate(R.layout.dialog_fragment_ask_for_survey_netigen_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonsBackgroundTints()
        setClickListeners()
    }

    private fun setButtonsBackgroundTints() {
        context?.let {
            surveyFragmentSendTextView.background.setTint(it, R.color.dialog_accent, PorterDuff.Mode.MULTIPLY)
            surveyFragmentNoTextView.background.setTint(it, R.color.dialog_neutral_button_bg, PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun setClickListeners() {
        setNegativeButtonListeners()
        setPositiveButtonListener()
    }


    private fun setPositiveButtonListener() {
        surveyFragmentSendTextView.setOnClickListener {
            dismissAllowingStateLoss()
            onClickYes?.let { it() }
        }
    }

    private fun setNegativeButtonListeners() {
        closeSurvey.setOnClickListener { close() }
        surveyFragmentNoTextView.setOnClickListener { close() }

    }

    private fun close() {
        dismissAllowingStateLoss()
        onClickNo?.let { it() }
    }
}