package com.lineup.jarviss.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lineup.jarviss.R
import com.lineup.jarviss.components.MessageBubble
import kotlinx.coroutines.launch

//@Composable
//fun ChatScreen(chatScreenViewModel: ChatScreenViewModel) {
//
//
//
//
//
//    var chatMessage by remember {
//        mutableStateOf("")
//    }
//
//    Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxSize()) {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 10.dp, bottom = 10.dp)
//        ) {
//            items(chatScreenViewModel.messageList) {
//                MessageBubble(it)
//            }
//        }
//        Row(modifier = Modifier.fillMaxWidth()) {
//            TextField(
//                value = chatMessage, onValueChange = {
//                    chatMessage = it
//                }, modifier = Modifier
//                    .padding(10.dp)
//            )
//        }
//
//        Button(onClick = {
//            chatScreenViewModel.addChatMessage(chatMessage)
//        }) {
//            Text(text = "Send")
//        }
//    }
//}

var scrollToPosition: () -> Unit = {}

@Composable
fun ChatScreen(chatScreenViewModel: ChatScreenViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = colorResource(id = R.color.light_blue))
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(bottomEnd = 50.dp),
                    elevation = 0.dp
                ) {
                    AddProfileDetails()
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 50.dp),
                backgroundColor = colorResource(
                    id = R.color.light_blue
                )
            ) {
                AddChatSection(chatScreenViewModel)
            }
        }
    }
}

@Composable
fun AddProfileDetails() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(start = 19.dp)
                )
                Text(
                    text = "J.A.R.V.I.S.S",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 19.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "Online",
                        style = TextStyle(
                            color = colorResource(id = R.color.message_blue),
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.profile1),
            contentDescription = "profile image",
            modifier = Modifier
                .padding(end = 15.dp)
                .size(90.dp)
                .clip(CircleShape)
                .border(
                    border = BorderStroke(
                        width = 3.dp, color = colorResource(
                            id = R.color.message_blue
                        )
                    ), shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun AddChatSection(chatScreenViewModel: ChatScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(19.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Messages",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(top = 15.dp)
                )
            }
            AddMessagingSection(chatScreenViewModel)
        }
        AddComposerSection(chatScreenViewModel)
    }
}

@Composable
fun AddMessagingSection(chatScreenViewModel: ChatScreenViewModel) {
    val scrollState = rememberLazyListState()
    Column(
        modifier = Modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            state = scrollState
        ) {
            items(chatScreenViewModel.messageList) {
                val index = chatScreenViewModel.messageList.indexOf(it)
                if (index % 2 == 1) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        MessageBubble(it, "User")
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        MessageBubble(it, "AI")
                    }
                }
            }
        }

        val coroutineScope = rememberCoroutineScope()
        scrollToPosition = {
            coroutineScope.launch {
                scrollState.animateScrollToItem(chatScreenViewModel.messageList.lastIndex)
            }
        }
    }
}

@Composable
fun AddComposerSection(chatScreenViewModel: ChatScreenViewModel) {
    var chatMessage by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TextField(
            value = chatMessage, onValueChange = {
                chatMessage = it
            },
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = colorResource(id = R.color.message_blue)
            ),
            maxLines = 1,
            placeholder = {
                Text(
                    text = "Type you message...",
                    style = TextStyle(color = Color.LightGray, fontSize = 15.sp)
                )
            },
            modifier = Modifier
                .weight(weight = 0.8f)
                .border(
                    border = BorderStroke(1.dp, color = Color.LightGray),
                    RoundedCornerShape(15.dp)
                )
        )
        Image(
            painter = painterResource(id = R.drawable.send2),
            contentDescription = "Send Button",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .clip(
                    CircleShape
                )
                .border(
                    border = BorderStroke(
                        1.dp,
                        color = colorResource(id = R.color.message_blue)
                    ), shape = CircleShape
                )
                .background(color = Color.White)
                .size(50.dp)
                .padding(10.dp)
                .clickable {
                    if (chatScreenViewModel.messageList.size % 2 == 1) {
                        if (chatMessage
                                .trim()
                                .isEmpty()
                        ) {
                            Toast
                                .makeText(context, "Type Some thing...", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            chatScreenViewModel.addChatMessage(chatMessage) {
                                scrollToPosition()
                            }
                            chatMessage = ""
                            scrollToPosition()
                        }
                    }
                }
        )
    }
}



