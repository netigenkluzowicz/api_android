package pl.netigen.coreapi.survey

import androidx.annotation.Keep

@Keep
data class SurveyData(
    val packageName: String,
    val answer1: String,
    val answer2: String
) {
    fun isValid() = answer1.length >= 4 && answer2.length >= 4
}
