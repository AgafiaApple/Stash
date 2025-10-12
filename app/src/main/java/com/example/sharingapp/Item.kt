package com.example.sharingapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Item class
 */
class Item(
    var title: String,
    var maker: String,
    var description: String,
    var dimensions: Dimensions,
    image: Bitmap?
) : java.io.Serializable {

    var status: String = "Available"
    var borrower: Contact? = null
    @Transient // This annotation is the equivalent of "transient" in Java
    var image: Bitmap? = null
    var image_base64: String? = null
    var id: String

    init { // This is the primary constructor's body
        addImage(image)
        this.id = UUID.randomUUID().toString() // Use Elvis operator for null check and default value
    }

    // NOTE: in a completed application, each id would need to be unique
    fun setId() {
        this.id = UUID.randomUUID().toString()
    }

    fun updateId(id: String) {
        this.id = id
    }

    fun addImage(newImage: Bitmap?) {
        newImage?.let { // Use let for null-safe operation
            image = it
            val byteArrayBitmapStream = ByteArrayOutputStream()
            it.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream)
            val b = byteArrayBitmapStream.toByteArray()
            image_base64 = Base64.encodeToString(b, Base64.DEFAULT)
        }
    }

    fun getImage(): Bitmap? {
        if (image == null && image_base64 != null) {
            val decodeString = Base64.decode(image_base64, Base64.DEFAULT)
            image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
        }
        return image
    }
}
