package sbis.faceinfo.presentation.search.contracts

import android.arch.lifecycle.MutableLiveData
import sbis.data.model.PersonSearch
import sbis.helpers.arch.contracts.MvpViewModel

interface SearchViewModelContract : MvpViewModel {

    enum class State {
        INITIAL, LOADING, DATA, ERROR
    }

    var state: MutableLiveData<State>

    var searchRequest: MutableLiveData<String>

    var searchPersons: MutableLiveData<List<PersonSearch>>
}