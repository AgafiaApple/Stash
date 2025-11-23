package com.example.sharingapp.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.MenuVertIcon
import com.example.sharingapp.ui.theme.outlineDark
import java.security.InvalidKeyException

enum class OptionsEnum(val action : String) {
    ADD("Add"), EDIT("Edit"), DELETE("Delete")
}


/*
 * We are making an abstract Option class so that the menu used for Item and the menu used
 * for Contact can be the same Composable class `MenuButton`
 */

abstract class Option(val option : OptionsEnum) {
    abstract val action : String
}

class ContactOption(option: OptionsEnum, private val shortened : Boolean = false) : Option(option) {
    override val action : String
        get() {
            if (shortened) return option.action

            return when(option) {
                OptionsEnum.ADD -> "Add Contact"
                OptionsEnum.DELETE -> "Delete Contact"
                OptionsEnum.EDIT -> "Edit Contact"
            }
        }
}

class ItemtOption(option: OptionsEnum, private val shortened : Boolean = false) : Option(option){
    override val action : String
        get() {
            if (shortened) return option.action

            return when(option) {
                OptionsEnum.ADD -> "Add Item"
                OptionsEnum.DELETE -> "Delete Item"
                OptionsEnum.EDIT -> "Edit Item"
            }
        }
}


@Composable
fun MenuButton(
    options : List<OptionsEnum>,
    onClickList : List<() -> Unit>,
    iconTint : Color = MaterialTheme.colorScheme.onPrimaryContainer,
    boxModifier : Modifier = Modifier
) {

    // each option must be associated with an onClick method
    assert(onClickList.size == options.size)
    val len = onClickList.size

    // `expanded` is used for knowing whether the menu is open or not
    var expanded by remember {mutableStateOf(false)}

    Box(
        modifier = boxModifier
    ) {
        IconButton(
            onClick = {expanded = !expanded},
        ) {
            Icon(
                ComposeIcon.asImageVector(MenuVertIcon()),
                contentDescription = "Menu button",
                tint = iconTint
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            for (idx in 0..<len) {
                val option = options[idx]
                val click = onClickList[idx]

                DropdownMenuItem(
                    text = {Text(option.action)},
                    onClick = {
                        click()
                        expanded = false
                    }
                )
            }

        } // end DropdownMenu block
    } // end Box block
} // end MenuButton