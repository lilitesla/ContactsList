package tesla.lili.contactlist.screens

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.budiyev.android.imageloader.ImageLoader
import kotlinx.android.synthetic.main.activity_profile.*
import tesla.lili.contactlist.R
import tesla.lili.contactlist.data.model.Contact
import java.io.Serializable

class ProfileActivity : AppCompatActivity() {

    companion object {
        private val CONTACT_DATA = "contact_pos"
        fun start(context: Context, contact: Contact) {
            val intent = Intent(context, ProfileActivity::class.java)

            intent.putExtra(CONTACT_DATA, contact as Serializable)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val contact = intent.extras.get(CONTACT_DATA) as Contact

        setSupportActionBar(toolbarProfile)
        supportActionBar?.title = contact.name

        showProfile(contact)

    }

    fun showProfile(contact: Contact) {

        val image: ImageView = findViewById(R.id.image)
        val name: TextView = findViewById(R.id.name)
        val phone: TextView = findViewById(R.id.phone)
        val email: TextView = findViewById(R.id.email)
        val address: TextView = findViewById(R.id.address)

        ImageLoader.with(this).from("https://some.url/image").load(image)

        name.setText(contact.name)
        phone.setText(contact.phone)
        email.setText(contact.email)
        address.setText(contact.address)

    }
}
