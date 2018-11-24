package sbis.domain.network.command.rpc

import android.os.Handler
import okhttp3.*
import sbis.App
import sbis.domain.network.HttpProtocol
import sbis.domain.network.command.rpc.common.RpcCommand
import java.io.IOException

class GetFullPersonInfoCommand(val personId: String) : RpcCommand {

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

        val handler = Handler()

        //FIXME: remove this code...
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBack.onFailure(call, e)

            }

            override fun onResponse(call: Call, response: Response) {
                val contentType = response.body()!!.contentType()
                val body = ResponseBody.create(contentType, generateUserFullInfo())

                handler.postDelayed({
                    callBack.onResponse(call, response.newBuilder().body(body).build())
                }, 700)
            }
        })
    }

    private fun generateUserFullInfo() =
        "{\"fullName\":\"Смирнов Петр\",\"id\":\"833f45f3-201e-4c5e-a626-2cc79f2896a1\",\"name\":\"Петр\",\"isSmoke\":\"true\",\"photoUrl\":\"https://picsum.photos/200/300\",\"params\":[{\"title\":\"Коммуникабельность\",\"value\":69},{\"title\":\"Дружелюбие\",\"value\":80},{\"title\":\"Ответственность\",\"value\":45},{\"title\":\"Стресс\",\"value\":94},{\"title\":\"Вероятность увольнения\",\"value\":24}],\"secondName\":\"Смирнов\"}"
}