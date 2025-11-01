package com.example.sharingapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class AddItemActivity : AppCompatActivity() {

    private val item_list = ItemList()
    private lateinit var title: EditText
    private lateinit var maker: EditText
    private lateinit var description: EditText
    private lateinit var length: EditText
    private lateinit var width: EditText
    private lateinit var height: EditText
    private lateinit var photo: ImageView
    private var image: Bitmap? = null
    private lateinit var context: Context

    private lateinit var save_button: Button

    // Activity Result Launcher for Taking Pictures
    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            bitmap?.let {
                image = it
                photo.setImageBitmap(image)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        // Initialize UI elements
        title = findViewById(R.id.title)
        maker = findViewById(R.id.maker)
        description = findViewById(R.id.description)
        length = findViewById(R.id.length)
        width = findViewById(R.id.width)
        height = findViewById(R.id.height)
        photo = findViewById(R.id.image_view)

        // set the save_button so we can respond to the onClick event
        save_button = findViewById(R.id.save_button)

        // Set default image
        photo.setImageResource(android.R.drawable.ic_menu_gallery)

        // Initialize context and load items
        context = applicationContext
        item_list.initializeItemList(context)
    }

    fun saveItem(view: View) {
        // Get text from EditText fields
        val titleStr = title.text.toString()
        val makerStr = maker.text.toString()
        val descriptionStr = description.text.toString()
        val lengthStr = length.text.toString()
        val widthStr = width.text.toString()
        val heightStr = height.text.toString()

        // Input validation
        if (titleStr.isEmpty()) {
            title.error = "Empty field!"
            return
        }
        if (makerStr.isEmpty()) {
            maker.error = "Empty field!"
            return
        }
        if (descriptionStr.isEmpty()) {
            description.error = "Empty field!"
            return
        }
        if (lengthStr.isEmpty()) {
            length.error = "Empty field!"
            return
        }
        if (widthStr.isEmpty()) {
            width.error = "Empty field!"
            return
        }
        if (heightStr.isEmpty()) {
            height.error = "Empty field!"
            return
        }

        // Create Dimensions object
        val dimensions = Dimensions(lengthStr, widthStr, heightStr)

        // Create Item object
        val item = Item(titleStr, makerStr, descriptionStr, dimensions)
        if (image != null) {
            item.addImage(image)
        }


        // Add item, save items, and navigate back to MainActivity
        // TODO: error with implmentation here! itemlist is not saved because the code is not gone into
//        save_button.setOnClickListener {
//            Log.d("BUTTONS", "User clicked the save button within activity_add_item.xml")
//            item_list.addItem(item)
//            item_list.saveItems(context)
//        }
        item_list.addItem(item)
        item_list.saveItems()

        // Start MainActivity and finish this activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    fun addPhoto(view: View) {
        // TODO: make the photo actually save in the item
        // Launch the camera using the Activity Result Launcher
        takePictureLauncher.launch(null)
    }

    fun deletePhoto(view: View) {
        // Delete the current photo
        image = null
        photo.setImageResource(android.R.drawable.ic_menu_gallery)
    }
}
