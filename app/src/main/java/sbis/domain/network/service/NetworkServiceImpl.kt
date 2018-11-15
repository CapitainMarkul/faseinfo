package sbis.domain.network.service

import sbis.data.model.presentation.PersonSearch
import sbis.domain.network.command.rpc.SearchPersonCommand

class NetworkServiceImpl : NetworkService {

    override fun searchPersons(searchRequest: String): List<PersonSearch> =
        SearchPersonCommand(searchRequest).execute()

//    override fun getPersonFullInfo(personId: UUID): PersonFullInfo {
//    }
}