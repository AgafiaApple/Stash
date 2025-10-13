package com.example.sharingapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EditItemActivity : AppCompatActivity() {
    private lateinit var item_list: ItemList
    private lateinit var item: Item
    private lateinit var context: Context

    private lateinit var contact_list: ContactList

    private var image : Bitmap? = null
    private final val REQUEST_CODE = 1
    private final val REQUEST_IMAGE_CAPTURE = 2
    private lateinit var photo: ImageView

    private lateinit var title: EditText
    private lateinit var maker: EditText
    private lateinit var description: EditText
    private lateinit var length: EditText
    private lateinit var width: EditText
    private lateinit var height: EditText
    private lateinit var borrower_spinner: Spinner
    private lateinit var borrower_tv: TextView
    private lateinit var status: Switch
    private lateinit var invisible: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        // set variables by referencing the xml
        title = findViewById(R.id.title);
        maker = findViewById(R.id.maker);
        description = findViewById(R.id.description);
        length = findViewById(R.id.length);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        borrower_spinner = findViewById(R.id.borrower_spinner);
        borrower_tv = findViewById(R.id.borrower_tv);
        photo = findViewById(R.id.image_view);
        status = findViewById(R.id.available_switch);
        invisible = findViewById(R.id.invisible);



        invisible.setVisibility(View.GONE);

        context = getApplicationContext();
        item_list.loadItems(context);
        contact_list.loadContacts(context);

        var adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            contact_list.getAllUsernames()
        )

        borrower_spinner.setAdapter(adapter)

        intent = getIntent()
        val pos = intent.getIntExtra("position", 0)

        item = item_list.getItem(pos)

        val contact = item.getBorrower()

        if (contact != null) {
            val contact_pos = contact_list.getIndex(contact)

            // TODO: make this more null-safe because it could be null
            borrower_spinner.setSelection(contact_pos!!)
        }

        title.setText(item.getTitle())
        maker.setText(item.getMaker())
        description.setText(item.getDescription())

        val dimensions = item.getDimensions()

        length.setText(dimensions.getLength())
        width.setText(dimensions.getWidth())
        height.setText(dimensions.getHeight())

        val status_str = item.getStatus()
        if (status_str.equals("Borrowed")) {
            status.setChecked(false)
        } else {
            borrower_tv.setVisibility(View.GONE)
            borrower_spinner.setVisibility(View.GONE)
        }

        image = item.getImage()
        if (image != null) {
            photo.setImageBitmap(image)
        } else {
            photo.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    } // end onCreate()

    fun deletePhoto(view : View) {
        image = null
        photo.setImageResource(android.R.drawable.ic_menu_gallery)

    } // end deletePhoto

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = intent.getExtras()

            // TODO: find a better alternative to the below line
            image = data?.extras?.get("data") as? Bitmap
            photo.setImageBitmap(image)

        }
    }

    fun deleteItem(view : View) {
        item_list.deleteItem(item)

        item_list.saveItems(context)

        // end EditItemActivity
        // Intent() requires a kotlin class
        intent = Intent(this, MainActivity::class.java)

        // go back to MainActivity
        startActivity(intent)
    }

    fun saveItem(view : View) {
        val title_str = title.getText().toString()
        val maker_str = maker.getText().toString()
        val description_str = description.getText().toString()
        val length_str = length.getText().toString()
        val width_str = width.getText().toString()
        val height_str = height.getText().toString()

        var contact: Contact? = null
        if (!status.isChecked()) {
            val borrower_str: String? = borrower_spinner.getSelectedItem().toString()

            contact = contact_list.getContactByUsername(borrower_str!!)
        }

        val dimensions = Dimensions(length_str, width_str, height_str)

        if (title_str == "") {
            title.setError("Empty field!")
            return
        }

        if (maker_str == "") {
            maker.setError("Empty field!")
            return
        }

        if (description_str == "") {
            description.setError("Empty field!")
            return
        }

        if (length_str == "") {
            length.setError("Empty field!")
            return
        }

        if (width_str == "") {
            width.setError("Empty field!")
            return
        }

        if (height_str == "") {
            height.setError("Empty field!")
            return
        }

        val id = item.id // Reuse the item id
        item_list.deleteItem(item)

        val updated_item = Item(title_str, maker_str, description_str, dimensions, image)

        val checked = status.isChecked()
        if (!checked) {
            updated_item.status = "Borrowed"
            updated_item.borrower = contact
        }

        item_list.addItem(updated_item)

        item_list.saveItems(context)


        // End EditItemActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    } // end saveItem()

    /**
     * Checked = Available
     * Unchecked = Borrowed
     */
    fun toggleSwitch(view: View?) {
        if (status.isChecked()) {
            // Means was previously borrowed, switch was toggled to available
            borrower_spinner.setVisibility(View.GONE)
            borrower_tv.setVisibility(View.GONE)
            item.borrower = null
            item.status = "Available"
        } else {
            // Means not borrowed
            if (contact_list.getSize() === 0) {
                // No contacts, need to add contacts to be able to add a borrower.
                invisible.setEnabled(false)
                invisible.setVisibility(View.VISIBLE)
                invisible.requestFocus()
                invisible.setError("No contacts available! Must add borrower to contacts.")
                status.setChecked(true) // Set switch to available
            } else {
                // Means was previously available
                borrower_spinner.setVisibility(View.VISIBLE)
                borrower_tv.setVisibility(View.VISIBLE)
            }
        }
    } // end toggleSwitch()

} // end class EditItemActivity