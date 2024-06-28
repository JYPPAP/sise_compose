package com.mania.mania_sise_240523

import ManiaSiseApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mania.mania_sise_240523.ui.theme.Mania_Sise_240523Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mania_Sise_240523Theme{
                ManiaSiseApp()
            }
        }
    }
}