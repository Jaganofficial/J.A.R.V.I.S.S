package com.lineup.jarviss.repository

import android.util.Log
import com.lineup.jarviss.data.DataOrException
import com.lineup.jarviss.model.JarvissMessage
import com.lineup.jarviss.network.JarvissApi
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject


class MessageRepository @Inject constructor(private val api: JarvissApi) {

    private val JarvissResponse = DataOrException<JarvissMessage, Boolean, Exception>()

    suspend fun getMessage(userMessage:String): DataOrException<JarvissMessage, Boolean, Exception> {
        try {
            JarvissResponse.loading=true
            val paramObject = JSONObject()
            paramObject.put("model", "text-davinci-003")
            paramObject.put("prompt", userMessage)
            paramObject.put("max_tokens",1000)
            paramObject.put("temperature",0)
            val requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramObject.toString())
            JarvissResponse.data=api.getMessage(requestBody)
            if(JarvissResponse.data.toString().isNotEmpty())
            {
                JarvissResponse.loading=false
            }
        } catch (e: Exception) {
            JarvissResponse.exception=e
            Log.d("Exception","Exception in getMessage in repository ${e.message}")
        }
        return JarvissResponse
    }
}