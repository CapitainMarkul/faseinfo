package sbis.faceinfo.presentation.search.router

import sbis.data.model.PersonSearch
import sbis.faceinfo.presentation.detailinfo.view.activity.DetailInfoActivity
import sbis.faceinfo.presentation.search.contracts.SearchRouterContract
import sbis.helpers.arch.base.BaseRouter
import sbis.helpers.arch.contracts.AndroidComponent
import java.util.*

class SearchRouter() : BaseRouter<SearchRouterContract.Presenter>(), SearchRouterContract.Router {

    override fun showDetailInfo(androidComponent: AndroidComponent, userId: UUID, tempUser: PersonSearch) {
        with(androidComponent.activity) {
            startActivity(DetailInfoActivity.createIntent(this, userId, tempUser))
        }
    }
}