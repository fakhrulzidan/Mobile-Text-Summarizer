package com.belajar.text_summarizer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //REPLACE NGROK_FORWARDING AFTER RUN: ngrok http 5000
    private const val BASE_URL = "https://NGROK-FORWARDING.ngrok-free.app/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl((BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}