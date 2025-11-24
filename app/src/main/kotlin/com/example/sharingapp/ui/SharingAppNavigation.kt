package com.example.sharingapp.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.sharingapp.AddBoxIcon
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.ContactsIcon
import com.example.sharingapp.EditPencilIcon
import com.example.sharingapp.HomeIcon
import com.example.sharingapp.ProfileIcon

/**
 * Destinations used in the [SharingApp]
 */
enum class AppDestination(val icon : ComposeIcon) {
    CONTACTS(ContactsIcon()),
    HOME(HomeIcon()),
    PROFILE(ProfileIcon()),

    EDIT_ITEM(EditPencilIcon()),

    ADD_ITEM(AddBoxIcon())
}

class SharingAppNavigation(navController: NavHostController) {

    val navigateToHome: () -> Unit = {
        // the navController holds the navigation graph and methods needed to move between
        // destinations in the graph
        navController.navigate(AppDestination.HOME) {
            // pop up to the start destination of the graph
            // to keep from building up a large stack of destinations
            // Note to self: learn more about this
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            // avoid multiple copies of the same destination
            // when reselecting the same item
            launchSingleTop = true
            // restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    val navigateToContacts: () -> Unit = {
        navController.navigate(AppDestination.CONTACTS) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

} // end class