package com.example.sharingapp.ui.utils

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sharingapp.model.Item
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.MenuVertIcon

@Composable
fun <T>ExpandableItemCard(
    item: T,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    cardTitle : String,
    cardSubtitle : String? = null,
    cardDescription : String? = null

    ){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.Spacing.Small)
            .animateContentSize( // automatically responds to height changes
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable {onToggle()}
            .clip(RoundedCornerShape(20.dp)) // TODO: change to use a predefined value from Dimens
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(Dimens.Spacing.Medium)
    ) {
        // Header row is visible always
        Row(
           verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            // TODO: add avatar circle here
            Spacer(Modifier.width(Dimens.Spacing.Medium))

            Column {
                // the name of the contact or item
                Text(cardTitle, style = MaterialTheme.typography.titleMedium)
                // add subtitle if provided
                if (cardSubtitle != null){
                    Text(cardSubtitle)
                }

            }
            Spacer(Modifier.weight(1f)) // fill remaining space -- is this a good idea?

            // vertical menu icon
            Icon(
                // TODO: make this button always visible in top-right
                imageVector = ComposeIcon.asImageVector(MenuVertIcon()),
                contentDescription = "More",
                // TODO: add `onClick` for the menu functionality
            )
        } // end Row block

        // DROP DOWN MENU
        if (isExpanded) {
            Spacer(Modifier.height(Dimens.Spacing.Medium))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // TODO: this should use dimensions predefined in Dimens
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                // TODO: Image placeholder here
            }

            Spacer(Modifier.height(Dimens.Spacing.Medium))
            if (cardDescription != null) {
                Text(cardDescription, style = MaterialTheme.typography.bodyMedium)
            } else {
                Text("No description", style = MaterialTheme.typography.bodyMedium)
            }


        }
    }

}
