package sbis.domain.network.command.rpc

import android.os.Handler
import okhttp3.*
import sbis.App
import sbis.domain.network.HttpProtocol
import sbis.domain.network.command.rpc.common.RpcCommand
import java.io.IOException

class SearchPersonCommand(private val searchRequest: String) : RpcCommand {

    override fun execute(okHttpClient: OkHttpClient, callBack: Callback) {
        //TODO: "https://httpbin.org/get?website=www.journaldev.com&tutorials=android"

        val url = HttpUrl.Builder()
            .scheme(HttpProtocol.HTTPS.protocolName)
            .host(App.get().getStorageService().getServerUrl())
            .addPathSegment("get")
            .addQueryParameter("website", "www.journaldev.com")
            .addQueryParameter("tutorials", "android")
            .build()

        val request = Request.Builder()
//            .header("Authorisation", "YOUR_TOKEN")
            .url(url.toString())
            .build()

        //FIXME: remove this code...
        val handler = Handler()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBack.onFailure(call, e)
            }

            override fun onResponse(call: Call, response: Response) {
                val contentType = response.body()!!.contentType()
                val body = ResponseBody.create(
                    contentType, when (searchRequest.length) {
                        in (5..7) -> generateFiveStubModels()
                        in (8..10) -> generateThirdStubModels()
                        else -> generateOneStubModels()
                    }
                )

                handler.postDelayed({
                    callBack.onResponse(call, response.newBuilder().body(body).build())
                }, 700)
            }
        })
    }

    private fun generateFiveStubModels() =
        "[{\"id\":\"6b5fcd9f-4071-4515-a7ff-cc62a856f0db\",\"name\":\"Семен\",\"photoUrl\":\"https://picsum.photos/200/280\",\"postName\":\"Мобильная разработка\",\"secondName\":\"Петров\"},{\"id\":\"d51a43ca-69e6-4cf1-a74a-59d45583eb8d\",\"name\":\"Николай\",\"photoUrl\":\"https://picsum.photos/200/300\",\"postName\":\"СБИС Диск\",\"secondName\":\"Порошенко\"},{\"id\":\"cff5c0dd-1406-4d66-a4b1-ea4be8163496\",\"name\":\"Анна\",\"photoUrl\":\"https://picsum.photos/200/300\",\"postName\":\"Тех. поддержка\",\"secondName\":\"Ивлеева\"},{\"id\":\"274dd18d-a41b-420a-aea9-c87f59fd6c5d\",\"name\":\"Нина\",\"photoUrl\":\"https://picsum.photos/200/300\",\"postName\":\"Юрист\",\"secondName\":\"Семенова\"},{\"id\":\"9b1b0c1b-5dff-40d1-9446-8e432635d68b\",\"name\":\"Георгий\",\"photoUrl\":\"https://picsum.photos/200/300\",\"postName\":\"СБИС Продажи\",\"secondName\":\"Павлов\"}]"

    private fun generateThirdStubModels() =
        "[{\"id\":\"d90972d8-0238-49d0-a131-a8d56582ff99\",\"name\":\"Семен\",\"photoUrl\":\"https://picsum.photos/200/280\",\"postName\":\"Мобильная разработка\",\"secondName\":\"Петров\"},{\"id\":\"fffe5391-3bad-4d38-9bae-10e1f0e20d5a\",\"name\":\"Николай\",\"photoUrl\":\"https://picsum.photos/200/300\",\"postName\":\"СБИС Диск\",\"secondName\":\"Порошенко\"},{\"id\":\"11608c8f-be06-480e-89de-914a3b07c6c6\",\"name\":\"Анна\",\"photoUrl\":\"https://picsum.photos/200/300\",\"postName\":\"Тех. поддержка\",\"secondName\":\"Ивлеева\"}]"

    private fun generateOneStubModels() =
        "[{\"id\":\"9b8a8e31-f26c-480f-a2bd-a9caacc02f58\",\"name\":\"Семен\",\"photoUrl\":\"https://picsum.photos/200/280\",\"postName\":\"Мобильная разработка\",\"secondName\":\"Петров\"}]"
}