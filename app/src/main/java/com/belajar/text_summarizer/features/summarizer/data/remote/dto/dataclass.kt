package com.belajar.text_summarizer.features.summarizer.data.remote.dto

data class SummaryRequest(
    val text : String,
    val num_sentences : Int
)

data class SummaryRespon(
    val summary: String,
    val stats: Map<String, Int>
)
