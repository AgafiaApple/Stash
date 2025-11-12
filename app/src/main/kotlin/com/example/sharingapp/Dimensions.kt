package com.example.sharingapp

import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(val length : Int,
                      val width : Int,
                      val height : Int) {
}