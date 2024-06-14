package pl.netigen.sampleapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.netigen.core.fragment.NetigenVMFragment
import pl.netigen.core.survey.Survey
import pl.netigen.coreapi.survey.SurveyEvent
import pl.netigen.coreapi.survey.SurveyEvent.Companion.defaultFirebaseEvent
import pl.netigen.sampleapp.BuildConfig
import pl.netigen.sampleapp.databinding.FragmentSurveyBinding
import timber.log.Timber

class SurveyFragment : NetigenVMFragment() {

    lateinit var binding: FragmentSurveyBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    private fun init() {
        Survey.showSurvey(binding.webView, BuildConfig.VERSION_NAME) { surveyEvent: SurveyEvent ->
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
