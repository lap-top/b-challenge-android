package com.example.broc.presentation.dialog_top

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.broc.presentation.navigation.Screen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun dialogTop(title: String, navController: NavController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Screen.HomeScreen.route)
            }) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(42.dp)
                )

            }
        },
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}