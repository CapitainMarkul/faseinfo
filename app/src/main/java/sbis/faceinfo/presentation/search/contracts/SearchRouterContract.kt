package sbis.faceinfo.presentation.search.contracts

import sbis.helpers.arch.contracts.AndroidComponent
import sbis.helpers.arch.contracts.MvpRouter

interface SearchRouterContract {

    interface Router : MvpRouter<Presenter> {
        fun showDetailInfo(androidComponent: AndroidComponent, userId: String)
        fun showSettingScreen(androidComponent: AndroidComponent)
    }

    interface Presenter : MvpRouter.Listener {

    }

}