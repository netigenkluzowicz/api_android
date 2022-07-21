package pl.netigen.core.newlanguage


data class LanguageModelDisplayable(
    val id: Int,
    var code: String = "",
    var language: String = "",
    var isSelected: Boolean = false,
)

