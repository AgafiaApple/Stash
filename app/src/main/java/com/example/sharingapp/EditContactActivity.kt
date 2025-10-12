package com.example.sharingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * Editing a pre-existing contact consists of deleting the old contact and adding a new contact with the old
 * contact's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
class EditContactActivity : AppCompatActivity() {

    private val contact_list = ContactList()
    private lateinit var contact: Contact
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        context = applicationContext
        contact_list.loadContacts(context)

        val intent = intent
        val pos = intent.getIntExtra("position", 0)

        contact = contact_list.getContact(pos)

        username = findViewById(R.id.username)
        email = findViewById(R.id.email)

        username.setText(contact.getUsername())
        email.setText(contact.getEmail())
    }

    fun saveContact(view: View) {

        val emailStr = email.text.toString()

        if (emailStr.isEmpty()) {
            email.error = "Empty field!"
            return
        }

        if (!emailStr.contains("@")) {
            email.error = "Must be an email address!"
            return
        }

        val usernameStr = username.text.toString()
        val id = contact.id // Reuse the contact id

        contact_list.deleteContact(contact)

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!contact_list.isUsernameAvailable(usernameStr) && !(contact.username == usernameStr)) {
            username.error = "Username already taken!"
            return
        }

        val updatedContact = Contact(usernameStr, emailStr, id)

        contact_list.addContact(updatedContact)
        contact_list.saveContacts(context)

        // End EditContactActivity
        finish()
    }

    fun deleteContact(view: View) {

        contact_list.deleteContact(contact)
        contact_list.saveContacts(context)

        // End EditContactActivity
        finish()
    }
}
