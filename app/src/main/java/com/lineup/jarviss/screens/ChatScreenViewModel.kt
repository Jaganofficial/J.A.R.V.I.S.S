package com.lineup.jarviss.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.lineup.jarviss.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(private val repository: MessageRepository) :
    ViewModel() {

    val messageList= mutableStateListOf("hii", "Hello", "How are you doing!")

    fun addChatMessage(message: String) {
        messageList.add(message)
        viewModelScope.launch {
            val messageData = repository.getMessage(userMessage = message)
            if (messageData.data != null) {
                if (messageList[messageList.lastIndex] == "Loading...")
                    messageList[messageList.lastIndex] =
                        (messageData.data?.choices?.get(0)?.text ?: "Sry, Please try again!")
                else
                    messageList.add(
                        messageData.data?.choices?.get(0)?.text ?: "Sry, Please try again!"
                    )
            } else {
                if (messageList[messageList.lastIndex] != "Loading...")
                    messageList.add("Loading...")
            }
        }
    }

}

data class CurrentData(val messageList:List<String>)