package com.belajar.text_summarizer.features.summarizer.data.remote

import com.belajar.text_summarizer.features.summarizer.data.remote.dto.SummaryRequest
import com.belajar.text_summarizer.features.summarizer.data.remote.dto.SummaryRespon
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("summarize")
    suspend fun summarize(@Body request: SummaryRequest): SummaryRespon
}