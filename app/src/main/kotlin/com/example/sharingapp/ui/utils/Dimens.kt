package com.example.sharingapp.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

// using object for the outer structure instead of class
// to simplify usage (calling Dimens.Spacing instead of Dimens().Spacing)
object Dimens {
    object Spacing {
        val Small = 8.dp
        val Medium = 16.dp
        val Large = 24.dp
    }

    object Card {
        val height = 140.dp
        val roundedCorner = 20.dp
        val elevation = 6.dp
        val shadowRadius = 2.dp
        val shadowSpread = 2.dp
        val shadowColor = Color(0x40000000)
        val shadowOffset = DpOffset(x = (-2).dp, y = 2.dp)
        val shadowAlpha = .5f

        val shadow = Shadow(
            radius = shadowRadius,
            spread = shadowSpread,
            color = shadowColor,
            offset = shadowOffset,
            alpha = shadowAlpha
        )
    }
}
