package pl.netigen.core.survey

import android.webkit.JavascriptInterface
import pl.netigen.coreapi.survey.SurveyAction
import timber.log.Timber

class SurveyInterface(val onNextAction: (surveyAction: SurveyAction, exitSurvey: Boolean) -> Unit) {

    @JavascriptInterface
    fun postMessage(message: String) {
        Timber.d(message)
        try {
            val surveyAction = SurveyAction.parseMessage(message)
            val action = when (surveyAction) {
                is SurveyAction.QuestionEntry -> surveyAction.action
                is SurveyAction.NormalEntry -> surveyAction.action
            }
            when (action) {
                "QUIT_YES", "EXIT" -> onNextAction(surveyAction, true)
                else -> onNextAction(surveyAction, false)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
