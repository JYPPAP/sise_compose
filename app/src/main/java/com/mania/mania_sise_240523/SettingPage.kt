package com.mania.mania_sise_240523

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SettingPage(navController: NavHostController) {

    val currentRoute = navController.currentDestination?.route
    Column {
        SettingInfo("정보", "버전", false, navController)
        SettingInfo("개인정보처리방침", "개인정보처리방침 보기", true, navController)
    }
}

@Composable
fun SettingInfo(title: String, subject: String, showTextButton: Boolean, navController: NavHostController, gameName: String = "") {
    Column {
        Box(
            Modifier
                .fillMaxWidth(),
        ){
            Text(
                text = "• $title",
                Modifier
                    .padding(16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Divider()
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = subject,
            )
            if (showTextButton) {
                IconButton(onClick = { navController.navigate("PolicyPage") }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "정책 페이지로 이동"
                    )
                }
            } else {
                Text(
                    text = "v 4. 1. 58",
                    color = Color.Black,
                )
            }
        }
        Divider()
    }
}