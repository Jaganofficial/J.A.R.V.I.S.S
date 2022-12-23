package com.lineup.jarviss.di

import com.lineup.jarviss.network.JarvissApi
import com.lineup.jarviss.repository.MessageRepository
import com.lineup.jarviss.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMessageApi(): JarvissApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(JarvissApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: JarvissApi) = MessageRepository(api)
}