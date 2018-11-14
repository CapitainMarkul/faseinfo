package sbis.faceinfo.presentation.detailinfo.contracts

import sbis.helpers.arch.contracts.MvpInteractor
import java.util.*

interface DetailInfoInteractorContract {

    interface Presenter : MvpInteractor.Listener {

    }

    interface Interactor : MvpInteractor<Presenter> {
        fun obtainUserFullInfo(userId: UUID)
    }
}