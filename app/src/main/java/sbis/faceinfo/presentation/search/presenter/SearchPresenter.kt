package sbis.faceinfo.presentation.search.presenter

import sbis.data.model.PersonSearch
import sbis.faceinfo.presentation.search.contracts.SearchInteractorContract
import sbis.faceinfo.presentation.search.contracts.SearchRouterContract
import sbis.faceinfo.presentation.search.contracts.SearchViewModelContract
import sbis.helpers.arch.base.BasePresenter
import sbis.helpers.arch.contracts.AndroidComponent

class SearchPresenter(
    val interactor: SearchInteractorContract.Interactor,
    val router: SearchRouterContract.Router
) : BasePresenter<SearchViewModelContract.ViewModel>(),
    SearchViewModelContract.Presenter,
    SearchInteractorContract.Presenter,
    SearchRouterContract.Presenter {

    override fun attachView(viewModel: SearchViewModelContract.ViewModel, component: AndroidComponent) {
        super.attachView(viewModel, component)
    }

    override fun detachView() {
        super.detachView()
    }

    override fun updateSearchRequest(searchRequest: String) {
        vm.searchPersons.value = interactor.searchPersons(searchRequest)
    }

    override fun onPersonSelected(person: PersonSearch) {
        router.showDetailInfo(androidComponent!!, person.id, person)
    }
}