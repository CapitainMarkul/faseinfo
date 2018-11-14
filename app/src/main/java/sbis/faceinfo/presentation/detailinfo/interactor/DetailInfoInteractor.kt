package sbis.faceinfo.presentation.detailinfo.interactor

import sbis.domain.network.service.NetworkService
import sbis.faceinfo.presentation.detailinfo.contracts.DetailInfoInteractorContract
import sbis.helpers.arch.base.BaseInteractor
import java.util.*

class DetailInfoInteractor(private val networkService: NetworkService) :
    BaseInteractor<DetailInfoInteractorContract.Presenter>(),
    DetailInfoInteractorContract.Interactor {

    override fun obtainUserFullInfo(userId: UUID) {
        //todo: obtainUserFullInfo
    }
}