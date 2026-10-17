package com.belajar.text_summarizer.features.summarizer.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.belajar.text_summarizer.features.summarizer.presentation.input.InputScreen
import com.belajar.text_summarizer.features.summarizer.presentation.result.ResultScreen

@Composable
fun AppNavigation() {
    // 1. Inisialisasi pengontrol navigasi
    val navController = rememberNavController()

    // 2. Buat NavHost dan tentukan layar pertama (startDestination)
    NavHost(navController = navController, startDestination = "input_screen") {

        // Daftarkan InputScreen
        composable("input_screen") {
            InputScreen(
                onNavigateToResult = {
                    // Berpindah ke ResultScreen saat tombol diklik
                    navController.navigate("result_screen")
                }
            )
        }

        // Daftarkan ResultScreen
        composable("result_screen") {
            ResultScreen(
                onBackClick = {
                    // Kembali ke layar sebelumnya (InputScreen)
                    navController.popBackStack()
                },
                // Sementara isi dengan nilai dummy (nanti bisa diganti dengan data asli dari API)
                realText = 1f,
                summaryText = 0.6f
            )
        }
    }
}