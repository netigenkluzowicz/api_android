package pl.netigen.coreapi.survey

import android.webkit.JavascriptInterface
import timber.log.Timber

class SurveyInterface(val onNextAction: (surveyAction: SurveyEvent) -> Unit) {

    @JavascriptInterface
    fun postMessage(message: String) {
        Timber.d(message)
        try {
            val surveyAction = SurveyEvent.parseMessage(message)
            onNextAction(surveyAction)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
