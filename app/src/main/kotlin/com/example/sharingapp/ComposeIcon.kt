package com.example.sharingapp

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource


abstract class ComposeItem {
    /*
     * ABSTRACT PROPERTIES that concrete subclasses must implement
    */
    abstract val name: String

    /**
     * The drawable resource ID for the item's icon
     * The @Drawable annotation means that only valid
     * R.drawable IDs can be used, giving us
     * compile-time safety
     */
    @get:DrawableRes
    abstract val image : Int

    /*
     * Enum that can be used for minor style adjustments
     * of the name
     */
    enum class NameCase {UPPER, LOWER, CAPITALIZED}

    /*
     * CONCRETE FUNCTION that any subclass can call
     */

    /**
     * A Composable fn that displays the item's
     * image and its name
     */
    @Composable
    fun Display(modifier : Modifier) {
        Image(
            painterResource(id=this.image),
            this.name,
            modifier = modifier
        )
        Text(text=this.name)
    } // end Display fn

    fun getNameCased(case : NameCase) : String {
        if (case == NameCase.LOWER) {return this.name.lowercase()}
        else if (case == NameCase.UPPER) {return this.name.uppercase()}
        else if (case == NameCase.CAPITALIZED) {

            val lowercased = this.name.lowercase()
            val firstLetterCapped = this.name.get(0).uppercase()
            val capitalized = this.name.replaceRange(0, 0,
                firstLetterCapped)

            return capitalized
        }
        else {
            throw IllegalArgumentException("Invalid NameCase object for the `case` parameter.")
        }
    } // end getNameCased


    companion object {
        /**
         * Takes in a child object of the ComposeItem type
         * and returns its image resource as an ImageVector
         */

        @Composable
        fun asImageVector(composeItem: ComposeItem): ImageVector {
            return ImageVector.vectorResource(composeItem.image)
        }
    }


} // end abstract class ComposeItem