package sbis.faceinfo.presentation.search.interactor

import sbis.data.model.PersonSearch
import sbis.domain.network.service.NetworkService
import sbis.faceinfo.presentation.search.contracts.SearchInteractorContract

class SearchInteractor(private val networkService: NetworkService) : SearchInteractorContract {

    override fun searchPersons(searchRequest: String): List<PersonSearch> =
        networkService.searchPersons(searchRequest)
}