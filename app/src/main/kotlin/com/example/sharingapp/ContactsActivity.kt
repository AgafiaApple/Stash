package com.example.sharingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/*
* Displays a list of all contacts
 */
class ContactsActivity : AppCompatActivity() {
    private var contactList = ContactList()
    private lateinit var myContacts : ListView
    private lateinit var adapter : ArrayAdapter<Contact>
    private var itemList = ItemList()
    private var activeBorrowersList = ContactList()

//    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        val context = getApplicationContext()
        contactList.loadContacts(context)
        itemList.initializeItemList(context)

        myContacts = findViewById<ListView>(R.id.my_contacts)!!
        adapter = ContactAdapter(this@ContactsActivity, contactList.getContacts()!!)
        myContacts.setAdapter(adapter)
        adapter.notifyDataSetChanged()

        // When contact is long clicked, this starts EditContactActivity
        myContacts.setOnItemLongClickListener(object : OnItemLongClickListener {
            override fun onItemLongClick(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ): Boolean {
                val contact = adapter.getItem(pos)

                val activeBorrowers: ArrayList<Contact> = itemList.getActiveBorrowers()
                activeBorrowersList.setContacts(activeBorrowers)

                // Prevent contact from editing an "active" borrower.
                if (activeBorrowersList.getContacts() != null) {
                    if (activeBorrowersList.hasContactByUsername(contact!!.username)) {
                        val text: CharSequence = "Cannot edit or delete active borrower!"
                        val duration = Toast.LENGTH_SHORT
                        Toast.makeText(context, text, duration).show()
                        return true
                    }
                }

                contactList.loadContacts(context) // Must load contacts again here
                val meta_pos: Int? = contactList.getIndex(contact!!)

                val intent = Intent(context, EditContactActivity::class.java)
                intent.putExtra("position", meta_pos)
                startActivity(intent)

                return true
            }
        })
    }




}