package com.mania.mania_sise_240523

import android.util.Log
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

    val currentRoute = navController.currentDestination?.route
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val originalList = listOf(
            "리그 오브 레전드", "오버워치 2", "발로란트", "로스트아크", "메이플스토리",
            "던전앤파이터", "피파 온라인 4", "서든어택", "아이온", "리니지",
            "리니지2", "블레이드 & 소울"
        )

        for (i in originalList.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (i < originalList.size) {
                    BoxItem(originalList[i], navController, Modifier.weight(1f))
                }
                if (i + 1 < originalList.size) {
                    BoxItem(originalList[i + 1], navController, Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun BoxItem(gameName: String, navController: NavHostController, modifier: Modifier = Modifier) {
    Log.d("BoxItem Check", "gameName: ${gameName}")
    Log.d("BoxItem Check", "navController: ${navController}")
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