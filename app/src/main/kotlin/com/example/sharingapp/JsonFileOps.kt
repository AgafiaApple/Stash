package com.example.sharingapp

import kotlinx.serialization.KSerializer
import java.io.File
import java.io.Serializable

/* Think of R as "return" and I as "intermediate"
 * The functions in JsonFileOps use an instance of I (a data class) that can be used
 *  with Jsonify
 * If R and I are compatible (e.g. R = Item, I = ItemData), then I is converted to
 * R and an instance of R is returned
*/
interface JsonFileOps<R, I : Jsonify<*>> { // `Jsonify<*>` means I can be anything that
    fun fromJsonFile(file : File) : R

    fun toJsonFile()

}