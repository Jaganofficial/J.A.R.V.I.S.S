package com.lineup.jarviss.network

import com.lineup.jarviss.model.JarvissMessage
import com.lineup.jarviss.util.Constants
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface JarvissApi {
    @POST("v1/completions")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer ${Constants.API_KEY}"
    )
    suspend fun getMessage( @Body body: RequestBody): JarvissMessage
}