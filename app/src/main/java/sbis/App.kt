package sbis

import android.app.Application
import android.os.Handler
import okhttp3.OkHttpClient
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
    }

    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .build()

    val handlerUi = Handler()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getNetworkService(): NetworkService =
        NetworkServiceImpl(okHttpClient)

    fun getStorageService(): StorageService =
        StorageServiceImpl(this)
}