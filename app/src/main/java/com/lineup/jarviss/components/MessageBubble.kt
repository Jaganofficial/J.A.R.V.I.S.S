package com.lineup.jarviss.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lineup.jarviss.R
import java.text.SimpleDateFormat
import java.util.*

@Preview
@Composable
fun MessageBubble(
    message: String = "Hello",
    from: String = "User"
){
    Column() {
        Surface(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 5.dp)
                ,
            color = colorResource(id = R.color.message_blue),
            shape = RoundedCornerShape(
                topStart = 15.dp,
                bottomEnd = 0.dp,
                bottomStart = 15.dp,
                topEnd = 15.dp
            )
        ){
            Text(
                text = message,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 17.sp,
                    //fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 15.dp,
                    top = 5.dp,
                    bottom = 5.dp
                )
            )
        }
    }
}





















//val currentTime = Calendar.getInstance().time
//val simpleDateFormat = SimpleDateFormat("h:mm a")
//val currentTime12HourFormat = simpleDateFormat.format(currentTime)
//
//if (from == "User") {
//    Column(horizontalAlignment = Alignment.End) {
//        Surface(
//            modifier = Modifier
//                .padding(top = 15.dp, bottom = 5.dp)
//                .border(
//                    border = BorderStroke(
//                        1.dp,
//                        color = colorResource(id = R.color.messageBubbleSent)
//                    ), shape = RoundedCornerShape(
//                        topStart = 15.dp,
//                        bottomEnd = 0.dp,
//                        bottomStart = 15.dp,
//                        topEnd = 15.dp
//                    )
//                ),
//            color = colorResource(id = R.color.message_blue),
//            shape = RoundedCornerShape(
//                topStart = 15.dp,
//                bottomEnd = 0.dp,
//                bottomStart = 15.dp,
//                topEnd = 15.dp
//            )
//        ) {
//            Text(
//                text = message,
//                style = TextStyle(
//                    color = Color.White,
//                    fontSize = 17.sp,
//                    //fontWeight = FontWeight.SemiBold
//                ),
//                modifier = Modifier.padding(
//                    start = 10.dp,
//                    end = 15.dp,
//                    top = 5.dp,
//                    bottom = 5.dp
//                )
//            )
//        }
//        Text(
//            text = currentTime12HourFormat,
//            style = TextStyle(color = Color.DarkGray, fontSize = 14.sp),
//            modifier = Modifier.padding(top = 3.dp)
//        )
//    }
//} else {
//    Column(horizontalAlignment = Alignment.Start) {
//        Surface(
//            modifier = Modifier
//                .padding(top = 15.dp, bottom = 5.dp)
//                .border(
//                    border = BorderStroke(
//                        1.dp,
//                        color = colorResource(id = R.color.messageBubble)
//                    ), shape = RoundedCornerShape(
//                        topStart = 0.dp,
//                        bottomEnd = 15.dp,
//                        bottomStart = 15.dp,
//                        topEnd = 15.dp
//                    )
//                ),
//            color = Color.White,
//            shape = RoundedCornerShape(
//                topStart = 0.dp,
//                bottomEnd = 15.dp,
//                bottomStart = 15.dp,
//                topEnd = 15.dp
//            )
//        ) {
//            Text(
//                text = message,
//                style = TextStyle(
//                    color = Color.Black,
//                    fontSize = 17.sp,
//                    //fontWeight = FontWeight.SemiBold
//                ),
//                modifier = Modifier.padding(
//                    start = 15.dp,
//                    end = 10.dp,
//                    top = 5.dp,
//                    bottom = 5.dp
//                )
//            )
//        }
//        Text(
//            text = currentTime12HourFormat,
//            style = TextStyle(color = Color.DarkGray, fontSize = 14.sp),
//            modifier = Modifier.padding(top = 3.dp)
//        )
//    }
//}