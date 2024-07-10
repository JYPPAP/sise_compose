package com.mania.mania_sise_240523

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class FavoriteItem(val gameName: String, val serverIdx: Int)
@Composable
fun FavoritePage(navController: NavHostController) {
    val currentRoute = navController.currentDestination?.route
    val favoriteList = listOf(
        FavoriteItem("리그 오브 레전드", 1),
        FavoriteItem("오버워치 2", 3),
        FavoriteItem("발로란트", 5),
    )
    var isEditMode by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteList) { item ->
            FavItem(
                gameName = item.gameName,
                serverIdx = item.serverIdx,
                navController = navController,
                isEditMode = isEditMode, // 편집 모드 상태 전달
//                onDelete = { favoriteList.remove(item) },
            )
        }
        if (isEditMode) { // 편집 모드일 때만 "편집 완료" 버튼 표시
            item {
                Button(
                    onClick = { isEditMode = false }, // 편집 모드 종료
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("편집 완료")
                }
            }
        }
    }
}


@Composable
fun FavItem(
    gameName: String,
    serverIdx:Int,
    navController: NavHostController,
    isEditMode: Boolean, // 편집 모드 상태 받기
//    onDelete: () -> Unit // 삭제 함수 받기
) {
    Log.d("ListItem Check", "gameName: ${gameName}")
    Log.d("ListItem Check", "navController: ${navController}")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isEditMode) {
                navController.navigate("ServerList/$gameName")
                navController.navigate("ServerDetail/Server$serverIdx")
            },
//        horizontalAlignment = Alignment.SpaceBetween(16.dp),
    ) {
        if (isEditMode) { // 편집 모드일 때만 "-" 버튼 표시
//            IconButton(onClick = onDelete) { // 삭제 함수 호출
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "삭제")
//            }
        }
        Text(
            text = gameName + " - " + serverIdx,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(8.dp),
        )
    }
}

