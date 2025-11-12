package com.example.sharingapp

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.*
import java.io.Serial
import java.io.Serializable

interface Jsonify <T> {
    // if item of type T is a data class, override serializer like:
        // override serializer = T.serializer()
    val serializer : KSerializer<T>
    // returns a String
    fun toJson(item : T) = Json.encodeToString(serializer, item)

    // returns type T
    fun fromJson(data : String) = Json.decodeFromString(serializer, data)

} // end Jsonify interface