package sbis.faceinfo.presentation.search.contracts

import sbis.data.model.PersonSearch
import sbis.helpers.arch.contracts.MvpPresenter

interface SearchPresenterContract : MvpPresenter<SearchViewModelContract> {

    fun updateSearchRequest(searchRequest: String)

    fun onPersonSelected(person: PersonSearch)
}