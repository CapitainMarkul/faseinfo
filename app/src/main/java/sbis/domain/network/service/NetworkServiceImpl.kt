package sbis.domain.network.service

import okhttp3.Callback
import okhttp3.OkHttpClient
import sbis.domain.network.command.rpc.GetFullPersonInfoCommand
import sbis.domain.network.command.rpc.SearchPersonCommand

class NetworkServiceImpl(private val okHttpClient: OkHttpClient) : NetworkService {

    override fun searchPersons(searchRequest: String, responseCallBack: Callback) =
        SearchPersonCommand(searchRequest).execute(okHttpClient, responseCallBack)

    override fun getPersonFullInfo(personId: String, responseCallBack: Callback)=
        GetFullPersonInfoCommand(personId).execute(okHttpClient, responseCallBack)
}