package sbis.domain.network.service.network

import okhttp3.Callback

interface NetworkService {
    fun searchPersons(searchRequest: String, responseCallBack: Callback)

//    TODO: getPersonFullInfo(...)
}