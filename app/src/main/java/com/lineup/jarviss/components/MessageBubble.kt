package com.lineup.jarviss.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MessageBubble(message: String = "Hello") {
    Surface(
        modifier = Modifier
            .padding(15.dp),
        color = Color.Blue,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            bottomEnd = 10.dp,
            bottomStart = 10.dp,
            topEnd = 10.dp
        )
    ) {
        Text(
            text = message,
            style = TextStyle(
                color = Color.White,
                fontSize = 15.sp
            ),
            modifier = Modifier.padding(
                start = 5.dp,
                end = 5.dp,
                top = 3.dp,
                bottom = 3.dp
            )
        )
    }
}