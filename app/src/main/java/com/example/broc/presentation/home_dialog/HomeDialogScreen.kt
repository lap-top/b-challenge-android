package com.example.broc.presentation.home_dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.broc.presentation.dialog_top.dialogTop
import com.example.broc.presentation.navigation.Screen
import com.example.broc.presentation.ui.theme.spacing


// Had this as it's own screen as I believe modal popup with forms are not great ui/ux
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDialogScreen(
    navController: NavController, viewModel: HomeDialogViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is HomeDialogViewModel.ValidationEvent.Success -> {
                    navController.navigate(Screen.CongratsScreen.route)
                }
            }
        }
    }
    Scaffold(
        topBar = { dialogTop("Request Email Invite", navController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(
                    vertical = paddingValues.calculateTopPadding() + MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.medium
                )
            ) { InviteForm(viewModel) }
        },

        )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteForm(viewModel: HomeDialogViewModel) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.margin)
    ) {
        if (state.responseError != null) {
            Text(state.responseError, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        OutlinedTextField(value = state.name,
            onValueChange = { viewModel.onEvent(HomeDialogEvent.NameChanged(it)) },
            isError = state.nameError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Name")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            label = { Text("Name") })
        if (state.nameError != null) {
            Text(text = state.nameError, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        OutlinedTextField(value = state.email,
            onValueChange = { viewModel.onEvent(HomeDialogEvent.EmailChanged(it)) },
            isError = state.emailError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            label = { Text("email") })

        if (state.emailError != null) {
            Text(text = state.emailError, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        OutlinedTextField(
            label = { Text("Confirm Email") },
            value = state.confirmEmail,
            onValueChange = { viewModel.onEvent(HomeDialogEvent.ConfirmEmailChanged(it)) },
            isError = state.confirmEmailError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Confirm Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
        )
        if (state.confirmEmailError != null) {
            Text(text = state.confirmEmailError, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Button(modifier = Modifier.fillMaxWidth(), enabled = !state.isLoading, onClick = {
            viewModel.onEvent(HomeDialogEvent.Submit)
        }) {
            Text("Submit", Modifier.padding(ButtonDefaults.ContentPadding))
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        if (state.isLoading) {
            Text("Please wait one moment.")
        }
    }
}
