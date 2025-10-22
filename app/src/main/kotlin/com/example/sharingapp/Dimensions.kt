package com.example.sharingapp

/**
 * Dimensions class
 */
data class Dimensions(
    var length: String,
    var width: String,
    var height: String
) {

    @JvmName("getLengthFlow")
    fun getLength(): String {
        return length
    }

    @JvmName("getWidthFlow")
    fun getWidth(): String {
        return width
    }

    @JvmName("getHeightFlow")
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
