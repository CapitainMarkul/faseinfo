package sbis.faceinfo.presentation.search.contracts

import sbis.helpers.arch.contracts.AndroidComponent
import java.util.*

interface SearchRouterContract {
    fun showDetailInfo(androidComponent: AndroidComponent, userId: UUID)
}