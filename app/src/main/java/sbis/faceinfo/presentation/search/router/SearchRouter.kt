package sbis.faceinfo.presentation.search.router

import sbis.faceinfo.presentation.detailinfo.view.activity.DetailInfoActivity
import sbis.faceinfo.presentation.search.contracts.SearchRouterContract
import sbis.helpers.arch.base.BaseRouter
import sbis.helpers.arch.contracts.AndroidComponent

class SearchRouter() : BaseRouter<SearchRouterContract.Presenter>(), SearchRouterContract.Router {

    override fun showDetailInfo(androidComponent: AndroidComponent, userId: String) {
        with(androidComponent.activity) {
            startActivity(DetailInfoActivity.createIntent(this, userId))
        }
    }
}