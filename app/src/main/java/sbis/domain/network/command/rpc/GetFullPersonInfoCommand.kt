package sbis.domain.network.command.rpc

import android.os.Handler
import okhttp3.*
import sbis.domain.network.command.rpc.common.RpcCommand
import java.io.IOException

class GetFullPersonInfoCommand(val personId: String) : RpcCommand {

    override fun execute(okHttpClient: OkHttpClient, callBack: Callback) {
        val url = "http://json-gen.com/rest/service/get/5U9SJ0898HCDpakluunowQnYVMNwNP"

//        val urlBuilder = HttpUrl.parse("https://httpbin.org/get")
//            ?.newBuilder()?.apply {
//                addQueryParameter("website", "www.journaldev.com")
//                addQueryParameter("tutorials", "android")
//            }

        val request = Request.Builder()
//            .header("Authorisation", "YOUR_TOKEN")
            .url(/*urlBuilder!!.build().toString() */url)
            .build()
        val handler = Handler()

        //FIXME: remove this code...
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

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