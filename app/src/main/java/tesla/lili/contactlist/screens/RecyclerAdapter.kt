package tesla.lili.contactlist.screens

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.budiyev.android.imageloader.ImageLoader
import tesla.lili.contactlist.R
import tesla.lili.contactlist.data.model.Contact

class RecyclerAdapter (val contactList: List<Contact>, val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contact_card, viewGroup, false)
        return ViewHolder(view);

    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {

        holder.name.setText(contactList[pos].name)
        ImageLoader.with(context).from(contactList[pos].picture).load(holder.image)

        holder.itemView.setOnClickListener {
            ProfileActivity.start(context, contactList[pos])
        }

    }





}