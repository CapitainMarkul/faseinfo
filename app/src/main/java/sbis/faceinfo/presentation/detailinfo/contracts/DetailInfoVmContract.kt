package sbis.faceinfo.presentation.detailinfo.contracts

import sbis.helpers.arch.contracts.MvpPresenter
import sbis.helpers.arch.contracts.MvpViewModel

interface DetailInfoVmContract {

    interface ViewModel : MvpViewModel {
        // TODO: enum State, state, errorMessage, userId, userFullInfo
        val userId: String
    }

    interface Presenter : MvpPresenter<ViewModel> {

    }
}