package sbis.faceinfo.presentation.search.viewModel

import android.arch.lifecycle.MutableLiveData
import sbis.data.model.PersonSearch
import sbis.faceinfo.presentation.search.contracts.SearchViewModelContract
import sbis.faceinfo.presentation.search.contracts.SearchViewModelContract.ViewModel.State
import sbis.helpers.arch.base.BaseViewModel

class SearchViewModel : BaseViewModel(), SearchViewModelContract.ViewModel {
    override var state = MutableLiveData<State>().apply { value = State.INITIAL }
    override var searchRequest = MutableLiveData<String>().apply { value = "" }
    override var searchPersons = MutableLiveData<List<PersonSearch>>().apply { value = emptyList() }
}