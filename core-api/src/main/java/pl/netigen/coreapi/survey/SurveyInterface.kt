package pl.netigen.coreapi.survey

import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import timber.log.Timber

class SurveyInterface(val onNextAction: (surveyEvent: SurveyEvent) -> Unit) {
    private val mainLooper = Handler(Looper.getMainLooper())

    @JavascriptInterface
    fun postMessage(message: String) {
        Timber.d(message)
        try {
            val surveyAction = SurveyEvent.parseMessage(message)
            mainLooper.post { onNextAction(surveyAction) }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
