package sbis.faceinfo.presentation.search.contracts

import sbis.data.model.presentation.PersonSearch
import sbis.helpers.arch.contracts.MvpInteractor

interface SearchInteractorContract {

    interface Presenter : MvpInteractor.Listener {

    }

    interface Interactor : MvpInteractor<Presenter> {
        fun searchPersons(searchRequest: String): List<PersonSearch>
    }
}