package sbis.faceinfo.presentation.search.router

import android.content.Intent
import sbis.data.model.presentation.PersonSearch
import sbis.faceinfo.presentation.detailinfo.view.activity.DetailInfoActivity
import sbis.faceinfo.presentation.search.contracts.SearchRouterContract
import sbis.faceinfo.presentation.setting.SettingActivity
import sbis.helpers.arch.base.BaseRouter
import sbis.helpers.arch.contracts.AndroidComponent

class SearchRouter() : BaseRouter<SearchRouterContract.Presenter>(), SearchRouterContract.Router {

    override fun showDetailInfo(androidComponent: AndroidComponent, user: PersonSearch) {
        with(androidComponent.activity) {
            startActivity(DetailInfoActivity.createIntent(this, user))
        }
    }

    override fun showSettingScreen(androidComponent: AndroidComponent) {
        with(androidComponent.activity) {
            startActivity(Intent(this, SettingActivity::class.java))
        }
    }
}