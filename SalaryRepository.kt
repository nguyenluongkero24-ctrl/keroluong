package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.ui.screen.MainSalaryApp
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.SalaryViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private val salaryViewModel: SalaryViewModel by viewModels {
        SalaryViewModel.Factory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            FirebaseApp.initializeApp(this)
        } catch (e: Exception) {
            android.util.Log.e("FirebaseInit", "Redundant manually-triggered FirebaseApp initialization failed: ${e.message}", e)
        }
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainSalaryApp(viewModel = salaryViewModel)
            }
        }
    }
}
