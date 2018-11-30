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
        const val CONNECTION_TIME_OUT = 60L
        const val SOCKET_TIME_OUT = 60L

        lateinit var instance: App

        fun get() = instance

        private const val PHOTO_HOST_URL = "fix-online.sbis.ru"
        private const val PHOTO_HOST_URL_2 = "online.sbis.ru"
    }


    private lateinit var okHttpClient: OkHttpClient

    private val cookie = CookieManager()

    val handlerUi = Handler()

    override fun onCreate() {
        super.onCreate()
        instance = this

        updateCookieSid()

        okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(SOCKET_TIME_OUT, TimeUnit.SECONDS)
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

    fun getNetworkClient(): OkHttpClient = okHttpClient

    fun updateCookieSid() {
        val userSid = getStorageService().getUserSid()
        cookie.addCookie(
            PHOTO_HOST_URL,
            Cookie.Builder()
                .domain(PHOTO_HOST_URL)
                .name("sid")
                .value("00000003-006d0d8f-00ba-20522160f7b438e8")
                .build()
        )

        cookie.addCookie(
            PHOTO_HOST_URL_2,
            Cookie.Builder()
                .domain(PHOTO_HOST_URL_2)
                .name("sid")
                .value("00000003-006d0d8f-00ba-20522160f7b438e8")
                .build()
        )
    }
}