package tesla.lili.contactlist.screens

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import tesla.lili.contactlist.data.model.Contact
import java.io.IOException
import java.io.Serializable
import com.google.gson.reflect.TypeToken




class GetDataService : IntentService("GetDataService") {

    companion object {
        const val ACTION_CONTACTS: String = "com.tesla.lili.action.CONTACTS"
    }

    override fun onHandleIntent(intent: Intent?) {
        val gson = Gson()
        val request = Request.Builder()
            .url("https://www.json-generator.com/api/json/get/cfFjiNihyW")
            .build()

        App.instance.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("--->", " ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {

                val contacts = gson.fromJson(response.body()?.charStream(), Array<Contact>::class.java)

                Log.e("--->", " res ok")

                val intentResult = Intent()
                intentResult.action = ACTION_CONTACTS
                intentResult.putExtra("CONTACTS_DATA", contacts.toList() as Serializable)
                intentResult.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
                sendBroadcast(intentResult)
            }
        })
    }


}