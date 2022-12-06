package pl.netigen.sampleapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_survey.*
import pl.netigen.core.fragment.NetigenVMFragment
import pl.netigen.core.survey.Survey
import pl.netigen.coreapi.survey.SurveyAction
import pl.netigen.sampleapp.BuildConfig
import pl.netigen.sampleapp.R
import timber.log.Timber

class SurveyFragment : NetigenVMFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_survey, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
        Survey.showSurvey(webView, BuildConfig.VERSION_NAME) { surveyAction: SurveyAction, isExit: Boolean ->
            Timber.d("xxx.+surveyAction = [$surveyAction], isExit = [$isExit]")
            if (isExit) {
                requireActivity().onBackPressed()
            }
        }
    }
}
