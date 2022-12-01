package com.example.broc.presentation.cancel

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.broc.R
import com.example.broc.presentation.navigation.Screen
import com.example.broc.presentation.ui.theme.spacing

@Composable
fun GoodbyeScreen(navController: NavController) {
    val context = LocalContext.current
    val imgLoader = ImageLoader.Builder(context).components {
        if (Build.VERSION.SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.margin),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = R.drawable.broc).apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imgLoader
            ),
            contentDescription = "Broccoli Mascot",
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Text(
            "Noo! It's sad to see you go, in the meantime stay happy and awesome.",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Button(modifier = Modifier.fillMaxWidth(),
            // call view and delete invite on datastore
            onClick = { navController.navigate(Screen.HomeScreen.route) }) {
            Text("Home", Modifier.padding(ButtonDefaults.ContentPadding))
        }
    }
}