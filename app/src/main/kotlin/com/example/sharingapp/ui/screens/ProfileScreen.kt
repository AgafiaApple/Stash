package com.example.sharingapp.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sharingapp.ui.ScreenTopBar
import com.example.sharingapp.ui.utils.NotImplemented

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues
) {
    var showEditProfileDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTopBar("Your Profile")

        Spacer(Modifier.height(16.dp))

        // placeholder avatar
        Surface(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape),
            color = Color.LightGray
        ) {}

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Username Placeholder",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Collector • Explorer • Journaler",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(24.dp))

        // fake stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProfileStat("42", "Items")
            ProfileStat("8", "Collections")
            ProfileStat("19", "Friends")
        }

        Spacer(Modifier.height(32.dp))

        // Section title
        Text(
            "About Me",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "This is just a placeholder bio for the placeholder profile screen!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(24.dp))

        // Placeholder button
        Button(
            onClick = { showEditProfileDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Profile")
        }
    } // end Column block

    if(showEditProfileDialog == true) {
        NotImplemented(
            onDismiss = {showEditProfileDialog = false},
            title = "Function not implemented",
            text = "The edit profile feature has not been implemented yet"
        )
    }

}

@Composable
fun ProfileStat(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
