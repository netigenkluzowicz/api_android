package pl.netigen.coreapi.donate

import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import timber.log.Timber

class DonateInterface(val onNextAction: (donateEvent: DonateEvent) -> Unit) {
    private val mainLooper = Handler(Looper.getMainLooper())

    @JavascriptInterface
    fun postMessage(message: String) {
        Timber.d(message)
        try {
            val surveyAction = DonateEvent.parseMessage(message)
            mainLooper.post { onNextAction(surveyAction) }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
