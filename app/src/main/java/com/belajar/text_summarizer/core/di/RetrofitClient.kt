package com.belajar.text_summarizer.core.di

import com.belajar.text_summarizer.features.summarizer.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //REPLACE NGROK_FORWARDING AFTER RUN: ngrok http 5000
    private const val BASE_URL = "https://a77d-180-254-105-168.ngrok-free.app/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl((BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}