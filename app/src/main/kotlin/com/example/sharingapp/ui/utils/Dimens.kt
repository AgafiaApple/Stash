package com.example.sharingapp.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.unit.dp

// using object for the outer structure instead of class
// to simplify usage (calling Dimens.Spacing instead of Dimens().Spacing)
object Dimens {
    object Spacing {
        val Small = 8.dp
        val Medium = 16.dp
        val Large = 24.dp
    }
}