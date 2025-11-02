package com.example.sharingapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.google.gson.JsonIOException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import org.json.JSONException
import java.io.BufferedWriter
import java.io.ByteArrayOutputStream
import java.util.*

/*
 * Item class
 */
class Item(var title: String, var maker: String, var description: String,
           var dimensions: Dimensions
) {

    var status: String = "Available"
    var borrower: Contact? = null

    @Transient // This annotation is the equivalent of "transient" in Java
    var image: Bitmap? = null
        get() {
            return base64ToBitmap(this.image_base64)
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

    fun toJsonString() : String {
        // convert the Item to a ItemData object
        val itemData = ItemData(this.title,
            this.maker,
            this.description,
            this.dimensions,
            this.image_base64)

        // use the ItemData object's toJson
        val jsonStr = JsonifyItemData.toJson(itemData)

        return jsonStr
    }

    // companion object is like a place to store static variables/functions
    companion object {
        fun base64ToBitmap(image_base64 : String?) : Bitmap? {
            if (image_base64 != null) {
                val decodeString = Base64.decode(image_base64, Base64.DEFAULT)
                val return_image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
                return return_image
            }
            return null
        }

        // converts a string into Json format 
        fun fromJsonString(str : String) : Item {
            lateinit var dataItem : ItemData
            try {
                dataItem = JsonifyItemData.fromJson(str)
            } catch(e : Exception) {
                println("An Exception occurred when attempting json to object conversion" + e.message)
            }

            val img_base64 = dataItem.image_base64
            val img = base64ToBitmap(img_base64)

            val item = Item(dataItem.title,
                dataItem.maker,
                dataItem.description,
                dataItem.dims)

            if (img != null) {
                item.image = img
            }

            return item
        }
    }



} // end Item class


@Serializable
data class ItemData(val title : String,
                    val maker : String,
                    val description : String,
                    val dims : Dimensions,
                    val image_base64 : String?)



// for converting to and from ItemData to the Json format the file data will be stored in
object JsonifyItemData : Jsonify<ItemData> {
    override val serializer: KSerializer<ItemData> = ItemData.serializer()

    override fun toJson(item: ItemData): String {
        return super.toJson(item)
    }
    override fun fromJson(data : String): ItemData {
        return super.fromJson(data)
    }
}
