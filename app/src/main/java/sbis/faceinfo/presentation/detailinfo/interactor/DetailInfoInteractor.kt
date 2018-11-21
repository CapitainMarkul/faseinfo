package sbis.faceinfo.presentation.detailinfo.interactor

import sbis.data.model.presentation.ItemParam
import sbis.data.model.presentation.PersonFullInfo
import sbis.domain.network.service.NetworkService
import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoInteractorContract
import sbis.helpers.arch.base.BaseInteractor
import java.util.*

class DetailInfoInteractor(private val networkService: NetworkService) :
    BaseInteractor<DetailInfoInteractorContract.Presenter>(),
    DetailInfoInteractorContract.Interactor {

    override fun obtainUserFullInfo(userId: String) {
        //todo: obtainUserFullInfo

        listener?.obtainedUserFulInfo(
            PersonFullInfo(
                id = UUID.randomUUID().toString(),
                name = "Петр",
                secondName = "Смирнов",
                params = listOf(
                    ItemParam("Коммуникабельность", 69),
                    ItemParam("Дружелюбие", 80),
                    ItemParam("Ответственность", 45),
                    ItemParam("Стресс", 94),
                    ItemParam("Вероятность увольнения", 24)
                )
            ), null
        )
    }
}