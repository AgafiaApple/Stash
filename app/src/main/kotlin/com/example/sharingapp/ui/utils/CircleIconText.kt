package com.example.sharingapp.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle

@Composable
fun CircleInitialIcon(name : String,
                      modifier: Modifier = Modifier,
                      size : Dp = 60.dp,
                      surfaceColor : Color = MaterialTheme.colorScheme.primaryContainer,
                      textColor : Color = MaterialTheme.colorScheme.onPrimaryContainer,
                      textStyle : TextStyle = MaterialTheme.typography.labelLarge
) {
    val initial = name.take(1).uppercase()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            // clip the box to a circular shape
            .clip(RoundedCornerShape(Dimens.Card.roundedCorner))
            .background(surfaceColor),
    ) {
        Text(
            text = initial,
            style = textStyle,
            color = textColor
        )
    }
}

//@Composable
//fun CircleInitialAvatar(
//    name: String,
//    modifier: Modifier = Modifier,
//    size: Dp = 50.dp
//) {
//    val initial = name.trim().firstOrNull()?.uppercase() ?: "?"
//    val backgroundColor = getColorFromName(name) // Uses a deterministic method to assign a color
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//            .size(size)
//            .clip(CircleShape)
//            .background(backgroundColor)
//    ) {
//        Text(
//            text = initial,
//            style = MaterialTheme.typography.labelLarge,
//            color = Color.Black
//        )
//    }
//}