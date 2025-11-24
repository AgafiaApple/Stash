package com.example.sharingapp.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.sharingapp.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun imageInRow(imagePainter : Painter? = null,
               modifier : Modifier = Modifier,
) {

    lateinit var image : Painter

    if (imagePainter != null) {
        image = imagePainter
    }
    else  {
        image = painterResource(R.drawable.image_placeholder)

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Spacing.Medium),
        contentAlignment = Alignment.Center
    ) {
        Image(
            image,
            modifier = modifier
                .clip(shape = RoundedCornerShape(Dimens.Card.roundedCorner)),
            contentDescription = "Image placeholder"
        )
    }
}