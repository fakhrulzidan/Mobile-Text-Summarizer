package com.belajar.text_summarizer.features.summarizer.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajar.text_summarizer.core.di.RetrofitClient
import com.belajar.text_summarizer.features.summarizer.data.remote.dto.SummaryRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SummarizerViewModel : ViewModel() {

    var sliderValue by mutableStateOf(1)

    private val _inputText = MutableStateFlow("")
    var inputText: StateFlow<String> = _inputText

    fun onInputChange(newText: String) {
        _inputText.value = newText
    }

    private val _summarizedText = MutableStateFlow("")
    val summarizedText: StateFlow<String> = _summarizedText

    fun clearSummary(){
        _summarizedText.value = ""
    }

    var loadingBtnSummary by mutableStateOf(false)


    fun summarize(){
        if (_inputText.value.isBlank() || loadingBtnSummary) return
        clearError()

        viewModelScope.launch {
            loadingBtnSummary = true
            try {
                _summarizedText.value =RetrofitClient.instance
                    .summarize(SummaryRequest(_inputText.value, sliderValue))
                    .summary
            } catch (e: Exception){
                errorMessage = "Error: ${e.message}"
            } finally {
                loadingBtnSummary = false
            }
        }
    }

    var errorMessage by mutableStateOf<String?>(null)
    fun clearError(){
        errorMessage = null
    }

}
