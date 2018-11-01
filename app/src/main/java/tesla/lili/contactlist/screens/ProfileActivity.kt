package tesla.lili.contactlist.screens

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.budiyev.android.imageloader.ImageLoader
import kotlinx.android.synthetic.main.activity_profile.*
import tesla.lili.contactlist.R
import tesla.lili.contactlist.data.model.Contact
import java.io.Serializable


class ProfileActivity : AppCompatActivity() {

    companion object {
        private const val CONTACT_DATA = "contact_pos"
        fun start(context: Context, contact: Contact) {
            val intent = Intent(context, ProfileActivity::class.java)

            intent.putExtra(CONTACT_DATA, contact as Serializable)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbarProfile)

        intent?.extras?.get(CONTACT_DATA)?.let {contact ->
            showProfile(contact as Contact)
            supportActionBar?.title = contact.name
        }
    }

    private fun showProfile(contact: Contact) {
        ImageLoader.with(this).from(contact.picture).load(image)

        name.text = contact.name
        phone.text = contact.phone
        email.text = contact.email
        address.text = contact.address

    }
}
