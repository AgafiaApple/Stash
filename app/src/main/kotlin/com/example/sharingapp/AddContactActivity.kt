package com.example.sharingapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.contextaware.ContextAware

class AddContactActivity : AppCompatActivity() {
    private lateinit var contactList : ContactList
    private lateinit var context: Context

    private lateinit var username: EditText
    private lateinit var email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        username = findViewById<EditText>(R.id.username)
        email = findViewById<EditText>(R.id.email)
        context = applicationContext
        contactList.loadContacts(context)

    }

    fun saveContact(view : View) {
        val username_str = username.getText().toString()
        val email_str = email.getText().toString()

        if (username_str.equals("")) {
            username.setError("Empty field!")
            return;
        }

        if (email_str.equals("")) {
            email.setError("Empty field!")
            return;
        }

        if (!email_str.contains("@")) {
            email.setError("Must be an email address!")
            return;
        }
        if (!contactList.isUsernameAvailable(username_str)) {
            username.setError("Username already taken!")
            return;
        }

        val contact = Contact(username_str, email_str, null)

        contactList.addContact(contact)
        contactList.saveContacts(context)

        // end AddContactsActivity
        finish()

    }
}