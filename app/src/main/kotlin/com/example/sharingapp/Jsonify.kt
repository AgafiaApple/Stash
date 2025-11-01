package com.example.sharingapp

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.*

interface Jsonify <T> {
    val serializer : KSerializer<T>
    fun toJson(item : T) = Json.encodeToString(serializer, item)
    fun fromJson(data : String) = Json.decodeFromString(serializer, data)

} // end Jsonify interface