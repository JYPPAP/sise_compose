package com.mania.mania_sise_240523

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun ServerList(serverName: String, navController: NavHostController) {
//    val navController = rememberNavController()
    val currentRoute = navController.currentDestination?.route

    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {
                Text(
                    text = "서버",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(5f)
                )
                Text(
                    text = "평균가",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = "등락",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(2f)
                )
            }
        }
        items(20) { i ->
            InfoLinkItem(i, navController)
        }
    }
}

@Composable
fun InfoLinkItem(subIdx: Number, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(1.dp, Color.Black)
            .clickable { navController.navigate("ServerDetail/Server$subIdx") },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "서버 $subIdx",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(5f)
        )
        Text(
            text = "평균가 $subIdx",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(3f)
        )
        Text(
            text = "등락 $subIdx",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(2f)
        )
    }
}