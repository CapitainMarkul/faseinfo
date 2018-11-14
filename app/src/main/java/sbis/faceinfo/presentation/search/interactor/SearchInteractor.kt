package sbis.faceinfo.presentation.search.interactor

import sbis.data.model.PersonSearch
import sbis.domain.network.service.NetworkService
import sbis.faceinfo.presentation.search.contracts.SearchInteractorContract
import sbis.helpers.arch.base.BaseInteractor

class SearchInteractor(private val networkService: NetworkService) : BaseInteractor<SearchInteractorContract.Presenter>(),
    SearchInteractorContract.Interactor {

    override fun searchPersons(searchRequest: String): List<PersonSearch> =
        networkService.searchPersons(searchRequest)
}