package com.mania.mania_sise_240523

import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun AddServer(gameName: String, navController: NavHostController) {
    val currentRoute = navController.currentDestination?.route
    var showAddDialog by remember { mutableStateOf(false) } // showAddDialog 상태 변수를 RankPage로 이동
    var selectedItem by remember { mutableStateOf<Int?>(null) }

    LazyColumn {
        items(20) { i ->
            ServerItem(
                index = i,
                navController = navController,
                showAddDialog = showAddDialog,
                onShowDialogChange = { showAddDialog = it },
                onItemSelected = { selectedItem = i }
            )
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("안 내") },
            text = { Text("게임명 : $gameName \n서버명 : $selectedItem\n\n관심게임에 등록하시겠습니까?") },
            confirmButton = {
                Button(onClick = {
                    showAddDialog = false
                    navController.navigate("FavoritePage")
                }) {
                    Text("확인")
                }
            },
            dismissButton = {
                Button(onClick = { showAddDialog = false }) {
                    Text("취소")
                }
            }
        )
    }
}

@Composable
fun ServerItem(
    index: Int,
    navController: NavHostController,
    showAddDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    onItemSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onShowDialogChange(true)
                onItemSelected(index) // 선택된 게임 이름 저장
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Server$index",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp),
        )
    }
}