package sbis.faceinfo.presentation.search.contracts

import sbis.data.model.PersonSearch
import sbis.helpers.arch.contracts.AndroidComponent
import java.util.*

interface SearchRouterContract {
    fun showDetailInfo(androidComponent: AndroidComponent, userId: UUID, tempUser: PersonSearch)
}