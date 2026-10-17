package com.belajar.text_summarizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.belajar.text_summarizer.features.summarizer.presentation.Navigation.AppNavigation
import com.belajar.text_summarizer.features.summarizer.presentation.input.InputScreen
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Panggil fungsi navigasi di sini
                    AppNavigation()
                }
            }
        }

    }
}
