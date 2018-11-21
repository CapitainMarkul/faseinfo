package sbis.faceinfo.presentation.detailinfo.contracts

import sbis.data.model.presentation.PersonFullInfo
import sbis.helpers.arch.contracts.MvpInteractor

interface DetailInfoInteractorContract {

    interface Presenter : MvpInteractor.Listener {
        fun obtainedUserFulInfo(user: PersonFullInfo, error: Throwable?)
    }

    interface Interactor : MvpInteractor<Presenter> {
        fun obtainUserFullInfo(userId: String)
    }
}