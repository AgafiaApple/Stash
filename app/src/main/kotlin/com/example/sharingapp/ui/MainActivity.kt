package com.example.sharingapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sharingapp.ComposeIcon
import com.example.sharingapp.ContactsIcon
import com.example.sharingapp.HomeIcon
import com.example.sharingapp.ProfileIcon
import com.example.sharingapp.data.AppContainer
import com.example.sharingapp.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharingApp(appContainer : AppContainer,isExpandedScreen : Boolean = false) {
    AppTheme {
        // to be passed down to the rest of the app hierarchy
        val navController = rememberNavController()
        val navActions = remember(navController) {
            SharingAppNavigation(navController)
        }

        // TODO : pass down/use this functionality
        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: AppDestination.HOME.name

        // TODO: isExpandedScreen
//        val isExpandedScreen = ...
        // TODO: any other size-aware properties

        /*
         * TODO: consider implementing Navigation layout element like ModelNavigationDrawer
         */

        Row {
//            if (isExpandedScreen) {
//                // TODO: adjust layout settings for larger screen sizes
//            }

            AppNavGraph(
                appContainer = appContainer,
                isExpandedScreen = isExpandedScreen,
                navController = navController,
                // TODO: pass down any navigation elements that need to be passed down
            )

        } // end Row block
    }
}