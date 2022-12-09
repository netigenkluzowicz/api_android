package pl.netigen.sampleapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_survey.*
import pl.netigen.core.fragment.NetigenVMFragment
import pl.netigen.core.survey.Survey
import pl.netigen.coreapi.survey.SurveyEvent
import pl.netigen.coreapi.survey.SurveyEvent.Companion.defaultFirebaseEvent
import pl.netigen.sampleapp.BuildConfig
import pl.netigen.sampleapp.R
import timber.log.Timber

class SurveyFragment : NetigenVMFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_survey, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
        Survey.showSurvey(webView, BuildConfig.VERSION_NAME) { surveyEvent: SurveyEvent ->
            Timber.d("xxx.+surveyAction = [$surveyEvent]")
            // Log event to firebase
            val defaultEvent = surveyEvent.defaultFirebaseEvent()
            sendFirebaseEvent(defaultEvent.name, defaultEvent.bundle)
            if (surveyEvent is SurveyEvent.ExitEvent) {
                // survey exits so navigate back
                requireActivity().onBackPressed()
            }
        }
    }

    // Log event to firebase
    private fun sendFirebaseEvent(name: String, bundle: Bundle) {
        // firebaseAnalytics.logEvent(name, bundle)
    }
}
