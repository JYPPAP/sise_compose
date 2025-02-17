package com.mania.mania_sise_240523

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PrivatePage(navController: NavHostController) {

    val currentRoute = navController.currentDestination?.route

    Box(/*modifier = Modifier.padding(paddingValues)*/) {
        Text(
            text = "개인정보처리방침",
            Modifier.padding(16.dp)
        )
    }
}