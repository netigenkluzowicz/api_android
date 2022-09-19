package pl.netigen.coreapi.survey

import androidx.annotation.Keep
import pl.netigen.coreapi.survey.ISurvey.Companion.MIN_SURVEY_TEXTS_LENGTH

@Keep
data class SurveyData(
    val packageName: String,
    val answer1: String,
    val answer2: String,
) {
    fun isValid() = answer1.length >= MIN_SURVEY_TEXTS_LENGTH && answer2.length >= MIN_SURVEY_TEXTS_LENGTH
}
