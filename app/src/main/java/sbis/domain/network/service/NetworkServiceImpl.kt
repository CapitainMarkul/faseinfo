package sbis.domain.network.service

import sbis.data.model.PersonFullInfo
import sbis.data.model.PersonSearch
import sbis.domain.network.command.SearchPersonCommand
import java.util.*

class NetworkServiceImpl : NetworkService {

    override fun searchPersons(searchRequest: String): List<PersonSearch> =
        SearchPersonCommand(searchRequest).execute()

//    override fun getPersonFullInfo(personId: UUID): PersonFullInfo {
//    }
}