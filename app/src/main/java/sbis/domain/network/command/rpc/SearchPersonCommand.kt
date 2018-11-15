package sbis.domain.network.command.rpc

import sbis.data.model.presentation.PersonSearch
import sbis.domain.network.command.rpc.common.RpcCommand
import java.util.*

class SearchPersonCommand(val searchRequest: String) :
    RpcCommand<List<PersonSearch>> {
    override fun execute(): List<PersonSearch> =
        when (searchRequest.length) {
            in (5..7) -> generateFiveStubModels()
            in (8..10) -> generateThirdStubModels()
            in (11..100) -> generateOneStubModels()
            else -> emptyList()
        }

    private fun generateFiveStubModels() =
        listOf(
            PersonSearch(
                UUID.randomUUID(),
                "Семен",
                "Петров",
                "Мобильная разработка",
                "img1.png"
            ),
            PersonSearch(
                UUID.randomUUID(),
                "Николай",
                "Порошенко",
                "СБИС Диск",
                "img2.png"
            ),
            PersonSearch(
                UUID.randomUUID(),
                "Анна",
                "Ивлеева",
                "Тех. поддержка",
                "img3.png"
            ),
            PersonSearch(UUID.randomUUID(), "Нина", "Семенова", "Юрист", "img4.png"),
            PersonSearch(
                UUID.randomUUID(),
                "Георгий",
                "Павлов",
                "СБИС Продажи",
                "img5.png"
            )
        )

    private fun generateThirdStubModels() =
        listOf(
            PersonSearch(
                UUID.randomUUID(),
                "Семен",
                "Петров",
                "Мобильная разработка",
                "img1.png"
            ),
            PersonSearch(
                UUID.randomUUID(),
                "Николай",
                "Порошенко",
                "СБИС Диск",
                "img2.png"
            ),
            PersonSearch(
                UUID.randomUUID(),
                "Анна",
                "Ивлеева",
                "Тех. поддержка",
                "img3.png"
            )
        )

    private fun generateOneStubModels() =
        listOf(
            PersonSearch(
                UUID.randomUUID(),
                "Семен",
                "Петров",
                "Мобильная разработка",
                "img1.png"
            )
        )
}