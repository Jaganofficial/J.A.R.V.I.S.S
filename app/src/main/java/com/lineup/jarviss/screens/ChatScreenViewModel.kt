package com.lineup.jarviss.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.lineup.jarviss.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(private val repository: MessageRepository) :
    ViewModel() {

    private var splashScreenValue= MutableStateFlow(true)
    val splashScreen=splashScreenValue.asStateFlow()
    val messageList= mutableStateListOf("Hello, I'm J.A.R.V.I.S.S!")

    init {
        viewModelScope.launch {
            delay(3000)
            splashScreenValue.value=false
        }
    }
    fun addChatMessage(message: String,onMessageAdded:()->Unit) {
        messageList.add(message)
        viewModelScope.launch {
            val messageData = repository.getMessage(userMessage = message)
            if (messageData.data != null) {
                if (messageList[messageList.lastIndex] == "Loading...")
                {
                    messageList[messageList.lastIndex] =
                        (messageData.data?.choices?.get(0)?.text?.trim() ?: "Sry, Please try again!")
                    onMessageAdded()
                }
                else
                {
                    messageList.add(
                        messageData.data?.choices?.get(0)?.text?.trim() ?: "Sry, Please try again!"
                    )
                    onMessageAdded()
                }
            } else {
                if (messageList[messageList.lastIndex] != "Loading...")
                    messageList.add("Loading...")
            }
        }
    }
}

data class CurrentData(val messageList:List<String>)