package sbis.faceinfo.presentation.detailinfo.contracts

import android.arch.lifecycle.MutableLiveData
import sbis.data.model.presentation.PersonFullInfo
import sbis.helpers.arch.contracts.MvpPresenter
import sbis.helpers.arch.contracts.MvpViewModel

interface DetailInfoVmContract {

    interface ViewModel : MvpViewModel {
        enum class State {
            INITIAL, LOADING, DATA, ERROR
        }

        var state: MutableLiveData<State>

        var errorMessage: MutableLiveData<String?>

        val userId: String
        val user: MutableLiveData<PersonFullInfo?>
    }

    interface Presenter : MvpPresenter<ViewModel> {

    }
}