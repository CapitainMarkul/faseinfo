package sbis.faceinfo.presentation.detailinfo.contracts

import android.arch.lifecycle.MutableLiveData
import sbis.helpers.arch.contracts.MvpPresenter
import sbis.helpers.arch.contracts.MvpViewModel
import java.util.*

interface DetailInfoVmContract {

    interface ViewModel : MvpViewModel {
        enum class State {
            INITIAL, LOADING, DATA, ERROR
        }

        var state: MutableLiveData<State>

        val userId: UUID
    }

    interface Presenter : MvpPresenter<ViewModel> {

    }
}