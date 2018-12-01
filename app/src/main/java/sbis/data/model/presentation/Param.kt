package sbis.data.model.presentation

enum class Param(
    val title: String,
    val isNegative: Boolean
) {
    RESPONSIBILITY("Ответственность", false),
    PROCRASTINATION("Прокрастинация", true),
    SOCIABILITY("Общительность", false),
    PUNCTUALITY("Пунктуальность", false),
    LEAVING_STATE("Вероятность ухода", true)
}