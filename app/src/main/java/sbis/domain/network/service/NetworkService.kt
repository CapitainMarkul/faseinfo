package sbis.domain.network.service

import sbis.data.model.PersonFullInfo
import sbis.data.model.PersonSearch
import java.util.*

interface NetworkService {
    fun searchPersons(searchRequest: String): List<PersonSearch>

//    fun getPersonFullInfo(personId: UUID): PersonFullInfo
}