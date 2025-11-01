package com.example.sharingapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Item class
 */
class Item(var title: String, var maker: String, var description: String,
           var dimensions: Dimensions
) {

//    // companion object is like a static variables
//    companion object {
//        // use next_key for knowing what the id of the item should be
//        var next_key = 1 // first item created will have key equal to 1
//        fun incrementKey() {
//            next_key = next_key + 1}
//    }

    var status: String = "Available"
    var borrower: Contact? = null

    @Transient // This annotation is the equivalent of "transient" in Java
    var image: Bitmap? = null
        get() {
            if (image == null && image_base64 != null) {
                val decodeString = Base64.decode(image_base64, Base64.DEFAULT)
                val return_image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
                return return_image
            }
            return image
        }

    // image_base64 is the string encoded version of the image
    var image_base64: String? = null

    var id: String

    // set the id and increment the id to the next one
    // TODO: make this correspond to the database key/id
    init {
//        this.id = UUID.randomUUID().toString()
        this.id = UUID.randomUUID().toString()

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

}


data class ItemData(val title : String,
                    val maker : String,
                    val description : String,
                    val dims : DimensionsData,
                    val image_base64 : String?)
