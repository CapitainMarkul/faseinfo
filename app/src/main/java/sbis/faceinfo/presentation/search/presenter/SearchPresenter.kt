package sbis.faceinfo.presentation.search.presenter

import sbis.faceinfo.presentation.search.contracts.SearchInteractorContract
import sbis.faceinfo.presentation.search.contracts.SearchPresenterContract
import sbis.faceinfo.presentation.search.contracts.SearchViewModelContract
import sbis.helpers.arch.base.BasePresenter
import sbis.helpers.arch.contracts.AndroidComponent

class SearchPresenter(
    val interactor: SearchInteractorContract
) : BasePresenter<SearchViewModelContract>(), SearchPresenterContract {

    override fun attachView(viewModel: SearchViewModelContract, component: AndroidComponent) {
        super.attachView(viewModel, component)
    }

    override fun detachView() {
        super.detachView()
    }

    override fun updateSearchRequest(searchRequest: String) {
        viewModel.searchPersons.value = interactor.searchPersons(searchRequest)
    }
}