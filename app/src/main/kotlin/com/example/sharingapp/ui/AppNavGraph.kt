package com.example.sharingapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sharingapp.data.AppContainer
import com.example.sharingapp.ui.screens.ContactsScreen
import com.example.sharingapp.ui.screens.ContactsViewModel
import com.example.sharingapp.ui.screens.EditItemScreen
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
    startDestination : String = AppDestination.HOME.name,
    innerPadding : PaddingValues = PaddingValues()
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
                isExpandedScreen = tempIsExpandedScreen,
                innerPadding = innerPadding,
                navController = navController
            )
        } // end HOME/Items composable

        // EDIT ITEM composable
        composable(
            // 1. Define the route to include a placeholder for the ID
            route = "${AppDestination.EDIT_ITEM.name}/{itemId}", // TODO: make this a more professional implementation later
            // 2. Define the arguments expected in route
            arguments = listOf(
                navArgument("itemId") { type = NavType.LongType }
            )
        ) { backStackEntry ->

            // 3. Extract the itemId from arguments
            val itemId = backStackEntry.arguments?.getLong("itemId") ?: -1L

            // 4. Get  ViewModel
            val itemsViewModel: ItemsViewModel = viewModel(
                factory = ItemsViewModel.provideFactory(
                    itemsRepository = appContainer.itemsRepository
                )
            )

            // 5. Pass the ID to the EditItemScreen (or let the VM fetch it)
            EditItemScreen(
                isExpandedScreen = isExpandedScreen,
                viewModel = itemsViewModel,
                innerPadding = innerPadding,
                itemId = itemId, // Pass ID, not the Item object
                navController = navController
            )
        }

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