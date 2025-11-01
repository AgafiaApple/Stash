package com.example.sharingapp

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Dimensions class
 */
@Serializable
data class Dimensions(var length : String, var width : String, var height  : String)

object JsonifyDimensions : Jsonify<Dimensions> {
    override val serializer: KSerializer<Dimensions> = Dimensions.serializer()

    override fun toJson(item : Dimensions) : String {
        return super.toJson(item)
    }

    override fun fromJson(data : String) : Dimensions {
        return super.fromJson(data)
    }

}
