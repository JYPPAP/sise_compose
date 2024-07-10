package com.mania.mania_sise_240523

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import kotlin.random.Random

@Composable
fun RankPage(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) } // showDialog 상태 변수를 RankPage로 이동
    var selectedItem by remember { mutableStateOf<String?>(null) } // 선택된 아이템 인덱스 저장
    val originalList = listOf(
        "리그 오브 레전드", "오버워치 2", "발로란트", "로스트아크", "메이플스토리",
        "던전앤파이터", "피파 온라인 4", "서든어택", "아이온", "리니지",
        "리니지2", "블레이드 & 소울", "검은사막", "아키에이지", "엘리온",
        "로스트아크", "이터널 리턴", "크레이지 아케이드", "카트라이더", "테일즈런너",
        "마비노기", "겟앰프드", "포트나이트", "배틀그라운드", "콜 오브 듀티",
        "사이퍼즈", "스타크래프트", "스타크래프트 2", "워크래프트 3", "디아블로 3"
    )

    LazyColumn {
        itemsIndexed(originalList) { index, gameName -> // itemsIndexed 함수 사용
            InfoRankItem(
                gameName = gameName,
                index = index, // 인덱스를 InfoRankItem에 전달
                navController = navController,
                showDialog = showDialog,
                onShowDialogChange = { showDialog = it },
                onItemSelected = { selectedItem = gameName }
            )
        }
    }

    // AlertDialog를 LazyColumn 외부로 이동
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("안 내") },
            text = { Text("더 많은 시세 정보를 보시겠습니까? (선택된 아이템: ${selectedItem})") }, // 선택된 아이템 표시
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("확인")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("취소")
                }
            }
        )
    }
}

@Composable
fun InfoRankItem(
    gameName: String,
    index: Int, // 인덱스 파라미터 추가
    navController: NavHostController,
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                onShowDialogChange(true)
                onItemSelected(gameName) // 선택된 게임 이름 저장
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${index + 1}", // 게임 이름 출력
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = gameName,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(6f)
        )
        RandomNumberWithArrow()
    }
    Divider()
}

@Composable
fun RandomNumberWithArrow() {
    val randomNumber = Random.nextInt(11) // 0부터 10까지 랜덤 숫자 생성

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { // 텍스트와 아이콘 간격 조정
        Text(text = "$randomNumber") // 랜덤 숫자 출력

        if (randomNumber == 0) {
            val color = Color.Black
            val icon = Icons.Filled.Menu

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color
            )
        } else if (randomNumber in 1..10) {
            // 숫자가 1부터 10 사이일 때 화살표 출력
            val color = if (randomNumber % 2 == 0) Color.Red else Color.Blue // 짝수면 빨간색, 홀수면 파란색
            val icon = if (randomNumber % 2 == 0) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown // 짝수면 위쪽, 홀수면 아래쪽 화살표

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color
            )
        }
    }
}