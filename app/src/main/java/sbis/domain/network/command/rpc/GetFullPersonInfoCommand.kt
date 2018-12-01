package sbis.domain.network.command.rpc

import android.os.Handler
import com.google.gson.Gson
import okhttp3.*
import sbis.App
import sbis.domain.network.HttpProtocol
import sbis.domain.network.command.rpc.common.RpcCommand
import java.io.IOException

class GetFullPersonInfoCommand(val personId: Int) : RpcCommand {

    private class FullInfoRequest(
        val sid: String,
        val user_id: Int
    )

    override fun execute(okHttpClient: OkHttpClient, callBack: Callback) {
        //TODO: https://1e2e499e.ngrok.io/contacts

        val requestJson = Gson().toJson(
            FullInfoRequest(
                sid = App.get().getStorageService().getUserSid(),
                user_id = personId
            )
        )

        val url = HttpUrl.Builder()
            .scheme(HttpProtocol.HTTPS.protocolName)
            .host(App.get().getStorageService().getServerUrl())
            .addPathSegment("get_user_info")
            .build()

        val requestBody = RequestBody.create(MediaType.parse("application/json"), requestJson)

        val request = Request.Builder()
            .url(url.toString())
            .post(requestBody)
            .build()

        //FIXME: FOR LOCAL TEST
        //TODO: "https://httpbin.org/get?website=www.journaldev.com&tutorials=android"
//        val url = HttpUrl.Builder()
//            .scheme(HttpProtocol.HTTPS.protocolName)
//            .host("httpbin.org")
//            .addPathSegment("get")
//            .addQueryParameter("website", "www.journaldev.com")
//            .addQueryParameter("tutorials", "android")
//            .build()
//
//        val request = Request.Builder()
////            .header("Authorisation", "YOUR_TOKEN")
//            .url(url.toString())
//            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBack.onFailure(call, e)
            }

            override fun onResponse(call: Call, response: Response) {
                //FIXME: FOR LOCAL TEST
//                val contentType = response.body()!!.contentType()
//                val body = ResponseBody.create(contentType, generateFiveStubModels())
//                callBack.onResponse(call, response.newBuilder().body(body).build())

                callBack.onResponse(call, response)
            }
        })
    }

    private fun generateFiveStubModels() =
        "{\n" +
                "  \"user_often_leaving\": true,\n" +
                "  \"user_sociability\": -1,\n" +
                "  \"user_procrastination\": 7,\n" +
                "  \"user_responsibility\": -1,\n" +
                "  \"user_punctuality\": 0,\n" +
                "  \"user_leaving_state\": 10\n" +
                "}"
}