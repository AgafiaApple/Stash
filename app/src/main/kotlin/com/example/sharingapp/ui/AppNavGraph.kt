package com.example.sharingapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sharingapp.data.AppContainer
import com.example.sharingapp.ui.screens.ContactsScreen
import com.example.sharingapp.ui.screens.ContactsViewModel
import com.example.sharingapp.ui.screens.ItemsScreen
import com.example.sharingapp.ui.screens.ItemsViewModel

// TODO: make dependent on user's device using conditional statements
const val tempIsExpandedScreen : Boolean = false
@Composable
fun AppNavGraph(
    appContainer : AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination : String = AppDestination.HOME.name
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        // HOME composable (where items are shown)
        composable(
            route = AppDestination.HOME.name
        ) {
//          navBackStackEntry ->  // TODO: implement backstack entry later
            val itemsViewModel : ItemsViewModel = viewModel(
                factory = ItemsViewModel.provideFactory(
                    itemsRepository = appContainer.itemsRepository,
                )
            )

            ItemsScreen(
                viewModel = itemsViewModel,
                onNavigateToContacts = { navController.navigate(AppDestination.CONTACTS.name)
                },
                isExpandedScreen = tempIsExpandedScreen
            )
        } // end HOME/Items composable

        // CONTACTS composable
        composable(
            route = AppDestination.CONTACTS.name,
        ) {
            val contactsViewModel : ContactsViewModel = viewModel(
                factory = ContactsViewModel.provideFactory(
                    contactsRepository = appContainer.contactsRepository
                )
            )

            ContactsScreen(
                viewModel = contactsViewModel,
                onNavigateToItems = { navController.navigate(AppDestination.CONTACTS.name)
                },
                isExpandedScreen = tempIsExpandedScreen
            )
        }
    } // end NavHost block

}