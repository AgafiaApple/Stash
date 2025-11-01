package com.example.sharingapp

import java.io.File
import java.io.Serializable

/* Think of R as "return" and I as "intermediate"
 * The functions in JsonFileOps use an instance of I (a data class) that extends
 *  `Serializable` and can be used with Jsonify
 * If R and I are compatible (e.g. R = Item, I = ItemData), then I is converted to
 * R and an instance of R is returned
*/
interface JsonFileOps<R, I : Serializable> : Jsonify<I> {
    fun fromJsonFile(file : File) : R

    fun toJsonFile(item : R)

}
