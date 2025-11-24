package com.example.sharingapp.ui.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

object Type {
    class Row {

        companion object{
            @Composable
            fun getTitle(): TextStyle {
                return MaterialTheme.typography.titleLarge
            }
            @Composable
            fun getSubtitle() : TextStyle {
                return MaterialTheme.typography.titleMedium
            }

            @Composable
            fun getSubtitleSmall() : TextStyle {
                return MaterialTheme.typography.titleSmall
            }

            @Composable
            fun getBody() : TextStyle {
                return MaterialTheme.typography.bodyMedium
            }
        }



    }

    class Bar {
        companion object {
            @Composable
            fun getTitle(): TextStyle {
                return MaterialTheme.typography.displayMedium
            }
            @Composable
            fun getSubtitle() : TextStyle {
                return MaterialTheme.typography.displayMedium
            }

            @Composable
            fun getSubtitleSmall() : TextStyle {
                return MaterialTheme.typography.displaySmall
            }

            @Composable
            fun getBody() : TextStyle {
                return MaterialTheme.typography.bodyMedium
            }
        }

    }
}