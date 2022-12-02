package pl.netigen.coreapi.survey

import android.os.Bundle
import androidx.annotation.Keep
import androidx.core.os.bundleOf
import pl.netigen.extensions.fromJson
import java.util.*

@Keep
sealed class SurveyAction {
    abstract val action: String

    @Keep
    data class NormalEntry(override val action: String, val survey: Int) : SurveyAction()

    @Keep
    data class QuestionEntry(override val action: String, val survey: Int, val question: Int) : SurveyAction()

    companion object {
        fun parseMessage(message: String): SurveyAction {
            if (message.contains("question")) return message.fromJson<QuestionEntry>()
            return message.fromJson<NormalEntry>()
        }

        fun SurveyAction.defaultEvent(): DefaultFirebaseEvent {
            val name = "survey_" + action.lowercase(Locale.US)
            val bundle = if (this is QuestionEntry) bundleOf("question" to question) else Bundle()
            return (DefaultFirebaseEvent(name, bundle))
        }
    }

    data class DefaultFirebaseEvent(val name: String, val bundle: Bundle)
}
