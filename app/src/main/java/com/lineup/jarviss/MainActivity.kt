package com.lineup.jarviss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lineup.jarviss.screens.ChatScreen
import com.lineup.jarviss.screens.ChatScreenViewModel
import com.lineup.jarviss.ui.theme.JARVISSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JARVISSTheme {
                // A surface container using the 'background' color from the theme
                val chatScreenViewModel:ChatScreenViewModel by viewModels()
                ChatScreen(chatScreenViewModel)
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