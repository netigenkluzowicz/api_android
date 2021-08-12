package pl.netigen.core.survey

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_fragment_survey_netigen_api.*
import pl.netigen.core.R
import pl.netigen.core.utils.BaseDialogFragment
import pl.netigen.coreapi.survey.SurveyData
import pl.netigen.extensions.setTint

/**
 * [BaseDialogFragment] used for show users "Ask For Survey" dialog, see [ISurvey]
 *
 */
class SurveyFragment : BaseDialogFragment() {

    companion object {
        fun newInstance(): SurveyFragment = SurveyFragment()
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
            surveyFragmentSendTextView.background.setTint(it, R.color.dialog_accent, PorterDuff.Mode.MULTIPLY)
            surveyFragmentCancelTextView.background.setTint(it, R.color.dialog_neutral_button_bg, PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun setClickListeners() {
        setNegativeButtonListener()
        setPositiveButtonListener()
    }


    private fun setPositiveButtonListener() {
        val packageName = surveyFragmentSendTextView.context.packageName
        surveyFragmentSendTextView.setOnClickListener {
            sendData(packageName)
            dismissAllowingStateLoss()
        }
    }

    private fun sendData(packageName: String) =
        viewModel.sendSurvey(SurveyData(packageName, surveyAnswer1.text.toString(), surveyAnswer2.text.toString()))


    private fun setNegativeButtonListener() = surveyFragmentCancelTextView.setOnClickListener { dismissAllowingStateLoss() }
}