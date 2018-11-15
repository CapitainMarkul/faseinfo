package sbis.domain.network.service

import sbis.data.model.presentation.PersonSearch

interface NetworkService {
    fun searchPersons(searchRequest: String): List<PersonSearch>

//    fun getPersonFullInfo(personId: UUID): PersonFullInfo
}