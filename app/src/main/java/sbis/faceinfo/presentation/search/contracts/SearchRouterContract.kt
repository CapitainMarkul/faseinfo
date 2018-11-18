package sbis.faceinfo.presentation.search.contracts

import sbis.data.model.presentation.PersonSearch
import sbis.helpers.arch.contracts.AndroidComponent
import sbis.helpers.arch.contracts.MvpRouter

interface SearchRouterContract {

    interface Router : MvpRouter<Presenter> {
        fun showDetailInfo(androidComponent: AndroidComponent, userId: String, tempUser: PersonSearch)
    }

    interface Presenter : MvpRouter.Listener {

    }

}