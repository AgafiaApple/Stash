package com.example.sharingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * ContactAdapter is responsible setting for what information is displayed in ListView entries.
 */
class ContactAdapter(context: Context, contacts: ArrayList<Contact>) :
    ArrayAdapter<Contact>(context, 0, contacts) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val context: Context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // getItem(position) gets the "contact" at "position" in the "contacts" ArrayList
        // (where "contacts" is a parameter in the ContactAdapter creator as seen above ^^)
        val contact = getItem(position)

        val username = "Username: ${contact?.username}" // Safe call and string interpolation
        val email = "Email: ${contact?.email}" // Safe call and string interpolation

        // Check if an existing view is being reused, otherwise inflate the view.
        val view = convertView ?: inflater.inflate(R.layout.contactlist_contact, parent, false)

        val usernameTv = view.findViewById<TextView>(R.id.username_tv)
        val emailTv = view.findViewById<TextView>(R.id.email_tv)
        val photo = view.findViewById<ImageView>(R.id.contacts_image_view)

        photo.setImageResource(android.R.drawable.ic_menu_gallery)

        usernameTv.text = username
        emailTv.text = email

        return view
    }
}
