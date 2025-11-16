package com.example.sharingapp

class HomeIcon : ComposeItem() {

    override val image = R.drawable.home

    override val name = "home"
}

class HeartIcon : ComposeItem() {
    override val image = R.drawable.fav_heart

    override val name = "favorite"
}

class AddBoxIcon : ComposeItem() {
    override val image = R.drawable.add_box

    override val name = "add"
}

class EditPencilIcon() : ComposeItem() {
    override val image = R.drawable.edit_pencil

    override val name = "edit"
}

class EditContactIcon() : ComposeItem() {
    override val image = R.drawable.person_edit

    override val name = "edit contact"
}

class ContactsIcon : ComposeItem() {
    override val image = R.drawable.people
    override val name = "contacts"
}

class ProfileIcon : ComposeItem() {
    override val image = R.drawable.person
    override val name = "profile"
}