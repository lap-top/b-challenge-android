package com.example.broc.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.broc.R
import com.example.broc.presentation.navigation.Screen
import com.example.broc.presentation.ui.theme.broccoliFont
import com.example.broc.presentation.ui.theme.spacing

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.margin),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.brocman), contentDescription = "Broccoli Mascot",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Text(
                "Broccoli & Co.",
                Modifier.padding(ButtonDefaults.ContentPadding),
                fontFamily = broccoliFont,
                fontWeight = FontWeight.Medium,
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                "Better Way, Better Day.",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate(Screen.InviteDialog.route) }) {
                Text(
                    "Request Invite",
                    Modifier.padding(ButtonDefaults.ContentPadding),
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}