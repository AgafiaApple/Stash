package com.example.sharingapp

/**
 * Dimensions class
 */
class Dimensions {
    lateinit var length: String
    lateinit var width: String
    lateinit var height: String



    fun getLength(): String {
        return length
    }


    fun getWidth(): String {
        return width
    }


    fun getHeight(): String {
        return height
    }

    fun getDimensions(): String {
        return "$length x $width x $height" // String interpolation
    }

    fun setDimensions(length: String, width: String, height: String) {
        this.length = length
        this.width = width
        this.height = height
    }
}

data class DimensionsData(val length: String, val width: String, val height : String)
