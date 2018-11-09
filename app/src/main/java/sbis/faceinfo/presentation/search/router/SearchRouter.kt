package sbis.faceinfo.presentation.search.router

import sbis.data.model.PersonSearch
import sbis.faceinfo.presentation.detailinfo.DetailInfoActivity
import sbis.faceinfo.presentation.search.contracts.SearchRouterContract
import sbis.helpers.arch.contracts.AndroidComponent
import java.util.*

class SearchRouter() : SearchRouterContract {

    override fun showDetailInfo(androidComponent: AndroidComponent, userId: UUID, tempUser: PersonSearch) {
        with(androidComponent.activity) {
            startActivity(DetailInfoActivity.createIntent(this, userId, tempUser))
        }
    }
}