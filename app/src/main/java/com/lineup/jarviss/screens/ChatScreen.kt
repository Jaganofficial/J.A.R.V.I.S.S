package com.lineup.jarviss.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lineup.jarviss.components.MessageBubble

@Composable
fun ChatScreen(chatScreenViewModel: ChatScreenViewModel) {
    var chatMessage by remember {
        mutableStateOf("")
    }

    Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            items(chatScreenViewModel.messageList) {
                MessageBubble(it)
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = chatMessage, onValueChange = {
                    chatMessage = it
                }, modifier = Modifier
                    .padding(10.dp)
            )
        }
        Button(onClick = {
            chatScreenViewModel.addChatMessage(chatMessage)
        }) {
            Text(text = "Send")
        }
    }
}