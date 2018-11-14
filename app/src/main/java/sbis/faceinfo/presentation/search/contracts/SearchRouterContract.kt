package sbis.faceinfo.presentation.search.contracts

import sbis.data.model.PersonSearch
import sbis.helpers.arch.contracts.AndroidComponent
import sbis.helpers.arch.contracts.MvpRouter
import java.util.*

interface SearchRouterContract {

    interface Router : MvpRouter<Presenter> {
        fun showDetailInfo(androidComponent: AndroidComponent, userId: UUID, tempUser: PersonSearch)
    }

    interface Presenter : MvpRouter.Listener {

    }

}