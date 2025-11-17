package com.example.sharingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AppBarRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.sharingapp.ui.theme.AppTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.painter.Painter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                SharingAppApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun SharingAppApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            ComposeIcon.asImageVector(it.icon),
                            it.name
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        } // end navigationSuiteItems
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Your Items") },
                    actions = {
                        // add the rightmost menu icon
                        Icon(
                            ComposeIcon.asImageVector(MenuVertIcon()),
                            "menu",

                            ) // end Icon
                    }, // end actions
                    colors = TopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        scrolledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        subtitleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
            }) { innerPadding ->
            // TODO: set up navigation like in that video you watched on NavControllers
                if (currentDestination == AppDestinations.CONTACTS) {

                }
        }
    }
}

enum class AppDestinations (val label : String, val icon : ComposeIcon){

    CONTACTS("contacts", ContactsIcon()),
    HOME("home", HomeIcon()),
    PROFILE("profile", ProfileIcon())
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}