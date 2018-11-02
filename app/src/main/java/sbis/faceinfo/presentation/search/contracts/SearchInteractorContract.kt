package sbis.faceinfo.presentation.search.contracts

import sbis.data.model.PersonSearch
import sbis.helpers.arch.contracts.MvpInteractor

interface SearchInteractorContract : MvpInteractor {
    fun searchPersons(searchRequest: String): List<PersonSearch>
}