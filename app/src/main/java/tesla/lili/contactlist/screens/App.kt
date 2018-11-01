package tesla.lili.contactlist.screens

import android.app.Application
import okhttp3.OkHttpClient


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var client: OkHttpClient

    override fun onCreate() {
        super.onCreate()

        instance = this
        client = OkHttpClient()

    }
}