package com.example.sharingapp.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sharingapp.model.Contact
import com.example.sharingapp.model.Dimensions
import com.example.sharingapp.model.Status
import kotlinx.serialization.Serializable

@Serializable
@Entity // tells Room to create a table for this class called "Item"
data class Item (
    var title: String,
    var maker: String,
    var description: String,
            // we can use @Embedded instead of making a TypeConverter b/c Dimensions is a data class
    @Embedded
    var dims : Dimensions,

    val useDao : Boolean = true,

    var status : Status = Status.AVAILABLE,

    val idx : Long? = null

) {

    @PrimaryKey(autoGenerate=true)
    val id : Long = idx ?: 0

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