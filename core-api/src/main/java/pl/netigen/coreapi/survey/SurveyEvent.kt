package pl.netigen.coreapi.survey

import android.os.Bundle
import androidx.annotation.Keep
import androidx.core.os.bundleOf
import pl.netigen.extensions.fromJson
import java.util.*

/**
 * Represents event from WebView Survey
 * @see <a href="https://github.com/netigenkluzowicz/apis_strapi/blob/develop/documentation/webview-survey.md">Webview survey</a>
 */
@Keep
sealed class SurveyEvent {
    abstract val action: String

    /**
     * Represents event from WebView Survey called when fragment should be closed
     */
    abstract class ExitEvent : SurveyEvent()

    /**
     * Represents event when user exits error dialog
     */
    data class QuitFromError(override val action: String = "QuitFromError") : ExitEvent()

    /**
     * Represents event from WebView Survey called when user quits survey without finishing it
     * @param action action name, it's "QUIT_YES"
     * @param survey id of survey
     * @param question index of last showed question
     */
    @Keep
    data class QuitYes(override val action: String, val survey: Int, val question: Int) : ExitEvent()

    /**
     * Represents event from WebView Survey called when user cancels exit of survey
     * @param action action name, it's "QUIT_NO"
     * @param survey id of survey
     * @param question index of last showed question
     */
    @Keep
    data class QuitNo(override val action: String, val survey: Int, val question: Int) : SurveyEvent()

    /**
     * Represents events from WebView Survey finishes survey
     * @param action action name, it's "FINISH"
     * @param survey id of survey
     */
    @Keep
    data class Finish(override val action: String, val survey: Int) : ExitEvent()

    /**
     * Represents events from WebView Survey other than above
     * @param action action name
     * @param survey id of survey
     */
    @Keep
    data class OtherEvent(override val action: String, val survey: Int) : SurveyEvent()

    companion object {
        fun parseMessage(message: String): SurveyEvent {
            return when {
                message.contains("QUIT_YES") -> message.fromJson<QuitYes>()
                message.contains("QUIT_NO") -> message.fromJson<QuitNo>()
                message.contains("FINISH") -> message.fromJson<Finish>()
                else -> message.fromJson<OtherEvent>()
            }
        }

        /**
         *  @return [DefaultFirebaseEvent] - should be used for logging event to Firebase
         */
        fun SurveyEvent.defaultFirebaseEvent(): DefaultFirebaseEvent {
            val name = "survey_" + action.lowercase(Locale.US)
            val bundle = when (this) {
                is QuitNo -> bundleOf("question" to question)
                is QuitYes -> bundleOf("question" to question)
                else -> Bundle()
            }
            return (DefaultFirebaseEvent(name, bundle))
        }
    }

    /**
     * Should be used for logging event to Firebase
     *
     * @param name - event name, default = "survey_" + action in lowercase
     * @param bundle - event bundle, contains question index or is empty
     */
    data class DefaultFirebaseEvent(val name: String, val bundle: Bundle)
}
