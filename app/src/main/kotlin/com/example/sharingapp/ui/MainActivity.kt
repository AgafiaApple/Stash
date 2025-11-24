package com.example.sharingapp.ui

import android.R
import android.app.Activity
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.ProfileIcon
import com.example.sharingapp.data.AppContainer
import com.example.sharingapp.ui.theme.AppTheme
import java.security.InvalidKeyException

/*  --- REMINDERS ---
 *
 *    // TODO LIST:
 *      * check that every composable accepts an optional modifier - good UI practice
 *      * make specification of spacing (.dp values) as high-level as possible (e.g. better to edit the spacing of a row than each item in the row)
 *      * implement functionality to adjust for different screen sizes
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val appContainer = (application as SharingApplication).container


        setContent {
            AppTheme(dynamicColor = false){

                Column{
                    // logo header that will always be seen
                    AppHeaderBar("Stash", {})

                    // title of the current screen


                    // get the NavHostController that tracks which screen we are in
                    val navController = rememberNavController()

                    // call the app's primary composable
                    SharingApp(appContainer, navController = navController)
                }


            }


        } // end setContact block

    } // end onCreate
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeaderBar(
    title : String,
    onClickProfile : () -> Unit
) {

        CenterAlignedTopAppBar(
            title = {
                Text(title, style = MaterialTheme.typography.titleLarge)
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            actions = {
                IconButton(onClick = onClickProfile) {
                    Icon(
                        imageVector = ComposeIcon.asImageVector(ProfileIcon()),
                        contentDescription = "Profile"
                    )
                }
            }
        ) // end CenterAlignedTopAppBar


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharingApp(appContainer : AppContainer,
               isExpandedScreen : Boolean = false,
               navController : NavHostController) {
        // to be passed down to the rest of the app hierarchy
        val navActions = remember(navController) {
            SharingAppNavigation(navController)
        }


        // TODO : pass down/use this functionality
        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: AppDestination.HOME.name

        // the heading of the current page
        var screenTitle = when (currentRoute) {
            AppDestination.HOME.name -> "Your Stash"
            AppDestination.PROFILE.name -> "Profile"
            AppDestination.CONTACTS.name -> "Your Contacts"
            else -> throw InvalidKeyException("Invalid route given")
        }

        // TODO: isExpandedScreen
//        val isExpandedScreen = ...
        // TODO: any other size-aware properties

        /*
         * TODO: consider implementing Navigation layout element like ModelNavigationDrawer
         */
        Scaffold(
            topBar = {
                ScreenTopBar(
                    screenTitle
                )
            },
            bottomBar = {
                AppBottomBar(
                    destinations = AppDestination.entries,
                    currentDestination = currentRoute,
                    onNavigateToDestination = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop =
                                true // avoid having multiple copies of same destination
                            restoreState =
                                true // restore state when reselecting a previously selected item
                        }
                    }
                )
            }, // end bottomBar setup
            containerColor = MaterialTheme.colorScheme.surface
        ) { innerPadding ->
            Row {
//            if (isExpandedScreen) {
//                // TODO: adjust layout settings for larger screen sizes
//            }
                // pass controller and padding down to graph

                AppNavGraph(
                    appContainer = appContainer,
                    isExpandedScreen = isExpandedScreen,
                    navController = navController,
                    // TODO: pass down any navigation elements that need to be passed down
                    modifier = Modifier.padding(innerPadding) // applies the scaffold's padding
                )

            } // end Row block
        }


} // end SharingApp fun