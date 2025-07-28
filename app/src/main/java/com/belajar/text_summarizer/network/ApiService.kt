package com.belajar.text_summarizer.network

import com.belajar.text_summarizer.SummaryRequest
import com.belajar.text_summarizer.SummaryRespon
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("summarize")
    suspend fun summarize(@Body request: SummaryRequest): SummaryRespon
}