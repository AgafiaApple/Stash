package com.example.sharingapp.model.impl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.sharingapp.model.Contact
import com.example.sharingapp.model.Dimensions
import com.example.sharingapp.model.Status

data class FakeItem_archived (

    var title: String,
    var maker: String,
    var description: String,
    // we can use @Embedded instead of making a TypeConverter b/c Dimensions is a data class
    var dims : Dimensions,
    val idx : Long

    ) {

        val id : Long = idx

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




    // because
    fun base64ToBitmap(image_base64 : String?) : Bitmap? {
        if (image_base64 != null) {
            val decodeString = Base64.decode(image_base64, Base64.DEFAULT)
            val return_image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
            return return_image
        }
        return null
    }

    // companion object for tracking the id for each FakeItem

}
