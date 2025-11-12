package com.example.sharingapp

import android.util.Log
import java.io.File
import com.google.gson.Gson
import java.io.FileWriter
import java.io.IOException

/*
 * Can be used to save the json Strings created by
 * the Jsonify interface
 */
class JsonFileOps { // `Jsonify<*>` means I can be anything that

    fun fromJsonFile(filepath : String) : String? {
        try {
            val file = File(filepath)
            val gson = Gson()
            val json_str = file.readText()

            return json_str

        } catch (e : IOException) {
            Log.d(null, "IOException caught!!!"
                    + " Returning `null`")

            return null
        }
    } // end fromJson()

    fun toJsonFile(filepath : String, json_str : String) : Boolean {
        try {
            val file = File(filepath)
            val gson = Gson()
            val writer = FileWriter(filepath)
            gson.toJson(json_str, writer)

            return true
        } catch (e : IOException) {
            Log.d(null, "IOException caught!!!"
                    + " Returning `false`")
            return false
        }

    } // end toJson()

}