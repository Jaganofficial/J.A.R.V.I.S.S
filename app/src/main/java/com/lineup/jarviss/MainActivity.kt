package com.lineup.jarviss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lineup.jarviss.screens.ChatScreen
import com.lineup.jarviss.screens.ChatScreenViewModel
import com.lineup.jarviss.ui.theme.JARVISSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chatScreenViewModel:ChatScreenViewModel by viewModels()
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                chatScreenViewModel.splashScreen.value
            }
        }
        setContent {
            JARVISSTheme {
                // A surface container using the 'background' color from the theme
                ChatScreen(chatScreenViewModel = chatScreenViewModel)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JARVISSTheme {
    }
}