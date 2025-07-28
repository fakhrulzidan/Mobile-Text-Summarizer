package com.belajar.text_summarizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SummarizerViewModel : ViewModel() {

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText

    private val _summarizedText = MutableStateFlow("")
    val summarizedText: StateFlow<String> = _summarizedText

    fun onInputChange(newText: String) {
        _inputText.value = newText
    }

    fun summarize() {
        viewModelScope.launch {
            // Misalnya model lokal / API call
            val result = dummySummarize(_inputText.value)
            _summarizedText.value = result
        }
    }

    private suspend fun dummySummarize(input: String): String {
        delay(1000) // simulasi proses
        return "Hasil ringkasan dari: \"$input\""
    }
}
