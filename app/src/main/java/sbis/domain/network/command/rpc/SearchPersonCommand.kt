package sbis.domain.network.command.rpc

import okhttp3.*
import sbis.App
import sbis.domain.network.HttpProtocol
import sbis.domain.network.command.rpc.common.RpcCommand
import java.io.IOException

class SearchPersonCommand(private val searchRequest: String) : RpcCommand {

    private class SearchRequest(
        val sid: String,
        val pid: String,
        val query_str: String,
        val contragent: String,
        val limit: Int
    )

    override fun execute(okHttpClient: OkHttpClient, callBack: Callback) {
        //TODO: https://0ea0f6d2.ngrok.io/contacts

//        val requestJson = Gson().toJson(
//            SearchRequest(
//                sid = App.USER_SID,
//                pid = App.USER_PID,
//                query_str = searchRequest,
//                contragent = "-2",
//                limit = 10
//            )
//        )
//
//        val url = HttpUrl.Builder()
//            .scheme(HttpProtocol.HTTPS.protocolName)
//            .host(App.get().getStorageService().getServerUrl())
//            .addPathSegment("contacts")
//            .build()
//
//        val requestBody = RequestBody.create(MediaType.parse("application/json"), requestJson)
//
//        val request = Request.Builder()
//            .url(url.toString())
//            .post(requestBody)
//            .build()

        //FIXME: FOR LOCAL TEST
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

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBack.onFailure(call, e)
            }

            override fun onResponse(call: Call, response: Response) {
                //FIXME: FOR LOCAL TEST
                val contentType = response.body()!!.contentType()
                val body = ResponseBody.create(contentType, generateFiveStubModels())
                callBack.onResponse(call, response.newBuilder().body(body).build())

//                callBack.onResponse(call, response)
            }
        })
    }

    private fun generateFiveStubModels() =
        "[\n" +
                "  {\n" +
                "    \"id\": \"15ebf3db-e352-48e4-b574-a81d89644a8b\",\n" +
                "    \"name\": \"Демо\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjpudWxsLCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": \"Права1(не трогать)\",\n" +
                "    \"secondName\": \"Демо1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"856fb45c-de33-4e9b-9c28-cea6140c8ec1\",\n" +
                "    \"name\": \"йцууу\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjpudWxsLCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": \"Сотрудники в корне теперь здесь\",\n" +
                "    \"secondName\": \"йцу\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"fcdb06d6-1b50-4aee-8336-b13d39e19e65\",\n" +
                "    \"name\": \"Демо\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjo4NjI4LCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": \"Ночной Дозор - я меч во тьме, дозорный на стене... Я огонь, что развеет холод, и свет, что приносит рассвет. Я горн, что разбудит спящих, и щит, что хранит людей.\",\n" +
                "    \"secondName\": \"Демо\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"c19d2843-172c-4084-bad0-c1f268743390\",\n" +
                "    \"name\": \"v\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjpudWxsLCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": \"123\",\n" +
                "    \"secondName\": \"GmfTest147 Демо\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"132402d1-93dc-4927-88ef-3edaba9db5af\",\n" +
                "    \"name\": \"Демо_онли\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjpudWxsLCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": \"00_НЕ_ТРОГАТЬ_тестовые данные\",\n" +
                "    \"secondName\": \"Демо_онли\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Татьяна\",\n" +
                "    \"secondName\": \"Демосюк\",\n" +
                "    \"id\": \"e5e85305-4b77-47cf-b5e8-cf5819ed1326\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjpudWxsLCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": \"Тензор\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Тест\",\n" +
                "    \"secondName\": \"Демо\",\n" +
                "    \"id\": \"c5014112-ec77-4d67-b444-0dffe5d7999c\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjpudWxsLCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": \"Демо группа\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Демон\",\n" +
                "    \"secondName\": \"Демонический\",\n" +
                "    \"id\": \"5b439232-3fcf-4efa-8f55-d94225f1f57f\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjpudWxsLCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": \"4101 Камчатский партнер\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"123\",\n" +
                "    \"secondName\": \"Демо\",\n" +
                "    \"id\": \"fc3bbb2b-4908-4e58-a37b-23b70f48c609\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjo3MTY0LCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Демо\",\n" +
                "    \"secondName\": \"Демо\",\n" +
                "    \"id\": \"811f302d-0278-48e4-a83d-55fe1ee34493\",\n" +
                "    \"photoUrl\": \"https://fix-online.sbis.ru/service/?id=3&method=PrivateFace.GetPhoto&protocol=5&params=eyJQaG90b0lkIjo2Njg3LCJTaXplIjo0MH0%3D\",\n" +
                "    \"postName\": null\n" +
                "  }\n" +
                "]"
}