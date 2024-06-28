package com.mania.mania_sise_240523

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomePage(navController: NavHostController) {
//    val navController = rememberNavController()
    val currentRoute = navController.currentDestination?.route

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in 1..6) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BoxItem("Game${i * 2 - 1}", navController, Modifier.weight(1f))
                BoxItem("Game${i * 2}", navController, Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun BoxItem(gameName: String, navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(2.dp, Color.Cyan, shape = RoundedCornerShape(16.dp))
            .clickable { navController.navigate("ServerList/$gameName") }
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = gameName,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp),
        )
    }
}