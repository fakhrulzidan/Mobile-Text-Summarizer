package com.belajar.text_summarizer.features.summarizer.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.belajar.text_summarizer.features.summarizer.presentation.SummarizerViewModel
import com.belajar.text_summarizer.features.summarizer.presentation.input.InputScreen
import com.belajar.text_summarizer.features.summarizer.presentation.result.ResultScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "input_screen") {
        composable("input_screen") {
            val viewModel: SummarizerViewModel = viewModel()
            InputScreen(
                onNavigateToResult = { originalText, summaryText ->
                    // Simpan data teks yang panjang dengan aman ke memori navigasi
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "original",
                        originalText
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "summary",
                        summaryText
                    )
                    navController.navigate("result_screen")
                },
                viewModel = viewModel
            )
        }

        composable("result_screen") {
            // Ambil data dari layar sebelumnya
            val originalText = navController.previousBackStackEntry?.savedStateHandle?.get<String>("original") ?: ""
            val summaryText = navController.previousBackStackEntry?.savedStateHandle?.get<String>("summary") ?: ""

            ResultScreen(
                onBackClick = { navController.popBackStack() },
                originalText = originalText, // Lempar ke ResultScreen
                summaryText = summaryText
            )
        }
    }
}