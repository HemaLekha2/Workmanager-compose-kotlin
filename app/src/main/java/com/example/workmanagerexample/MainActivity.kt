package com.example.workmanagerexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.workmanagerexample.ui.MainScreen
import com.example.workmanagerexample.ui.theme.WorkManagerExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkManagerExampleTheme {
                    MainScreen()
            }
        }
    }
}

