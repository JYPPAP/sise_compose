package com.mania.mania_sise_240523

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SearchPage(navController: NavHostController) {
    val currentRoute = navController.currentDestination?.route
    var searchText by remember { mutableStateOf("") }
    val originalList = listOf(
        "리그 오브 레전드", "오버워치 2", "발로란트", "로스트아크", "메이플스토리",
        "던전앤파이터", "피파 온라인 4", "서든어택", "아이온", "리니지",
        "리니지2", "블레이드 & 소울", "검은사막", "아키에이지", "엘리온",
        "로스트아크", "이터널 리턴", "크레이지 아케이드", "카트라이더", "테일즈런너",
        "마비노기", "겟앰프드", "포트나이트", "배틀그라운드", "콜 오브 듀티",
        "사이퍼즈", "스타크래프트", "스타크래프트 2", "워크래프트 3", "디아블로 3"
    )
    var filteredList by remember { mutableStateOf(originalList) }

    Column {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("검색") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )
        LaunchedEffect(searchText) {
            filteredList = if (searchText.isBlank()) {
                originalList
            } else {
                originalList.filter { it.contains(searchText) }
            }
        }
        Divider()
        LazyColumn {
            items(filteredList) { item ->
                ListItem(gameName = item, navController = navController, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ListItem(gameName: String, navController: NavHostController, modifier: Modifier) {
    Log.d("ListItem Check", "gameName: ${gameName}")
    Log.d("ListItem Check", "navController: ${navController}")
    Box(
        modifier = modifier
            .fillMaxWidth()
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
    Divider()
}