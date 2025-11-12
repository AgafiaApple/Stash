package com.example.sharingapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import kotlinx.serialization.Serializable

@Serializable
data class Item (val title: String,
            val maker: String,
            val description: String,
            val dims : Dimensions) {

    // we can do this because our class is a data class
    val id : Int get() = hashCode()

    var status = Status.AVAILABLE

    // default is that there is no borrower
    var borrower : Contact? = null

    var image_base64 : String? = null

    // we probably do not need to store both a bitmap and a base64 format b/c that
    // means the class is doing more work than necessary since it never uses Bitmap format
//    @Transient // This annotation is the equivalent of "transient" in Java
//    var image : Bitmap? = null
//        get() {
//            // the app will only ever need the base64 format
//            return base64ToBitmap(this.image_base64)
//        }


}

// because
fun base64ToBitmap(image_base64 : String?) : Bitmap? {
    if (image_base64 != null) {
        val decodeString = Base64.decode(image_base64, Base64.DEFAULT)
        val return_image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
        return return_image
    }
    return null
}