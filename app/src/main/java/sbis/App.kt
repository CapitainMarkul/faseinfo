package sbis

import android.app.Application
import android.os.Handler
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Cookie
import okhttp3.OkHttpClient
import sbis.domain.network.CookieManager
import sbis.domain.network.service.network.NetworkService
import sbis.domain.network.service.network.NetworkServiceImpl
import sbis.domain.repository.storage.StorageService
import sbis.domain.repository.storage.StorageServiceImpl
import java.util.concurrent.TimeUnit

class App : Application() {
    companion object {
        const val CONNECTION_TIME_OUT = 10L

        lateinit var instance: App

        fun get() = instance

        private const val PHOTO_HOST_URL = "fix-online.sbis.ru"
        const val USER_SID = "00000003-00066e3e-00ba-b977e62f351fda3b"
        const val USER_PID = "fcdb06d6-1b50-4aee-8336-b13d39e19e65"
    }


    private lateinit var okHttpClient: OkHttpClient

    private val cookie = CookieManager().apply {
        addCookie(
            PHOTO_HOST_URL,
            Cookie.Builder()
                .domain(PHOTO_HOST_URL)
                .name("sid")
                .value(USER_SID)
                .build()
        )

        addCookie(
            PHOTO_HOST_URL,
            Cookie.Builder()
                .domain(PHOTO_HOST_URL)
                .name("pid")
                .value(USER_PID)
                .build()
        )
    }

    val handlerUi = Handler()

    override fun onCreate() {
        super.onCreate()
        instance = this

        okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .cookieJar(cookie)
            .build()

        Picasso.setSingletonInstance(
            Picasso.Builder(applicationContext)
                .downloader(OkHttp3Downloader(okHttpClient))
                .build()
        )
    }

    fun getNetworkService(): NetworkService =
        NetworkServiceImpl(okHttpClient)

    fun getStorageService(): StorageService =
        StorageServiceImpl(this)
}