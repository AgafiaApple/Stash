package com.example.sharingapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Item class
 */

class Item {
    lateinit var title: String
    lateinit var maker: String
    lateinit var description: String
    lateinit var dimensions: Dimensions


    var status: String = "Available"
    var borrower: Contact? = null
    @Transient // This annotation is the equivalent of "transient" in Java
    var image: Bitmap? = null

    // image_base64 is the string encoded version of the image
    var image_base64: String? = null

    lateinit var id: String


    // NOTE: in a completed application, each id would need to be unique
    fun setId() {
        this.id = UUID.randomUUID().toString()
    }

    fun updateId(id: String) {
        this.id = id
    }

    fun addImage(newImage: Bitmap?) {
        newImage?.let { // Use let for null-safe operation
            this.image = it
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

    fun getDescription() : String {
        return this.description
    }


    fun getTitle() : String {
        return this.title
    }

    fun getMaker() : String {
        return this.maker
    }

    fun getDimensions() : Dimensions {
        return this.dimensions
    }

    fun getBorrower() : Contact? {
        return this.borrower
    }

    fun getStatus() : String {
        return this.status
    }
}

data class ItemData(val title : String,
                    val maker : String,
                    val description : String,
                    val dims : DimensionsData,
                    val image_base64 : String?)
