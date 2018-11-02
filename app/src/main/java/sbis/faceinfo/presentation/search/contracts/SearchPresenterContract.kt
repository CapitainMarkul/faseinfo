package sbis.faceinfo.presentation.search.contracts

import sbis.helpers.arch.contracts.MvpPresenter

interface SearchPresenterContract : MvpPresenter<SearchViewModelContract> {

    fun updateSearchRequest(searchRequest: String)
}