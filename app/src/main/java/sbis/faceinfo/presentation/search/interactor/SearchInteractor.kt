package sbis.faceinfo.presentation.search.interactor

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import sbis.data.mapper.transformToPresentation
import sbis.data.mapper.transformToPresentationList
import sbis.data.model.gson.PersonSearchGson
import sbis.domain.network.service.network.NetworkService
import sbis.faceinfo.presentation.search.contracts.SearchInteractorContract
import sbis.helpers.arch.base.BaseInteractor
import java.io.IOException

class SearchInteractor(private val networkService: NetworkService) :
    BaseInteractor<SearchInteractorContract.Presenter>(),
    SearchInteractorContract.Interactor {

    override fun obtainPersons(searchRequest: String) {
        networkService.searchPersons(searchRequest, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runUi { listener?.obtainedPersons(emptyList(), e) }
            }

            override fun onResponse(call: Call, response: Response) {
                val resultJson = response.body()!!.string()

                try {
                    val type = object : TypeToken<List<PersonSearchGson>>() {}.type
                    val persons = Gson().fromJson<List<PersonSearchGson>>(resultJson, type)

                    runUi { listener?.obtainedPersons(persons.transformToPresentationList(), null) }
                } catch (e: Exception) {
                    runUi { listener?.obtainedPersons(emptyList(), Exception(resultJson)) }
                }
            }
        })
    }
}