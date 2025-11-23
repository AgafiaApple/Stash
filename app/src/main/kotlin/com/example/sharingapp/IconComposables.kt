package com.example.sharingapp

import androidx.compose.ui.graphics.Color

/*
 * Below are classes for accessing each icon utilized in SharingApp.
 * They inherit from the Compose
 */

class HomeIcon : ComposeIcon() {

    override val image = R.drawable.home

    override val name = "home"

}

class HeartIcon : ComposeIcon() {
    override val image = R.drawable.fav_heart

    override val name = "favorite"
}

class AddBoxIcon : ComposeIcon() {
    override val image = R.drawable.add_box

    override val name = "add"
}

class EditPencilIcon() : ComposeIcon() {
    override val image = R.drawable.edit_pencil

    override val name = "edit"
}

class EditContactIcon() : ComposeIcon() {
    override val image = R.drawable.person_edit

    override val name = "edit contact"
}

class ContactsIcon : ComposeIcon() {
    override val image = R.drawable.people
    override val name = "contacts"
}

class ProfileIcon : ComposeIcon() {
    override val image = R.drawable.person
    override val name = "profile"
}

class MenuVertIcon : ComposeIcon() {
    override val image = R.drawable.menu_vertical_icon
    override val name = "menu"
}