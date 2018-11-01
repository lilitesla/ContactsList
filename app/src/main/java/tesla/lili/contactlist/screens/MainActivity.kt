package tesla.lili.contactlist.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import tesla.lili.contactlist.R
import tesla.lili.contactlist.data.model.Contact
import android.content.IntentFilter
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration




class MainActivity : AppCompatActivity() {

    var contactList: List<Contact> = listOf()
    private var myReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setTitle(getString(R.string.app_name))

        val getData = Intent(this, GetDataService::class.java)
        startService(getData)
    }

    private fun showContacts() {

        val contactRecycler = findViewById<RecyclerView>(R.id.contact_recycler)
        val recyclerAdapter = RecyclerAdapter(contactList, this)
        val layoutManager = LinearLayoutManager(this)

        contactRecycler.adapter = recyclerAdapter
        contactRecycler.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            contactRecycler.context,
            layoutManager.orientation
        )
        contactRecycler.addItemDecoration(dividerItemDecoration)
    }

    private fun registerReceiver() {
        if (this.myReceiver != null) return
        myReceiver = ContactsReceiver()
        val filter = IntentFilter()
        filter.addAction(GetDataService.ACTION_CONTACTS)
        registerReceiver(myReceiver, filter)
    }

    inner class ContactsReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            contactList = intent.extras.get("CONTACTS_DATA") as List<Contact>
            showContacts()

        }
    }


}
