package sbis

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import okhttp3.OkHttpClient
import sbis.domain.network.service.NetworkService
import sbis.domain.network.service.NetworkServiceImpl

class App : Application() {
    companion object {
        lateinit var instance: App

        fun get() = instance
    }

    private val okHttpClient = OkHttpClient()

    override fun onCreate() {
        super.onCreate()
        instance = this

        Fresco.initialize(this)
    }

    fun getNetworkService(): NetworkService = NetworkServiceImpl(okHttpClient)
}