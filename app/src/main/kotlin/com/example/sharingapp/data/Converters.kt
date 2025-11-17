package com.example.sharingapp.data

import androidx.room.TypeConverter
import com.example.sharingapp.model.Status

/**
 * For data fields that are not data classes (and therefore cannot use @Embedded)
 * but still need to be stored and accessed using a DAO
 */
class Converters {
    /*
     * ------ CONVERTERS FOR Status OBJECTS (enum) ------
     */
    @TypeConverter
    fun fromStatus(status : Status): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(statusStr : String) : Status {
        return Status.valueOf(statusStr)
    }
}