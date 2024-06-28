package com.mania.mania_sise_240523

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AppHeader(title: String, info: String?, navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val isServerDetailRoute = currentRoute?.startsWith("ServerDetail/") == true

    // 상단 앱 바 구성
    Column() {
        /*탑 바*/
        Row(
            modifier = Modifier
                .height(68.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Blue, Color.Green)
                    )
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            if (title === "Main") {
                ClickableIcon(Icons.Default.Place, "HomePage", navController)
                ClickableIcon(Icons.Default.Home, "HomePage", navController)
                ClickableIcon(Icons.Default.MoreVert, "SettingPage", navController)
            } else {
                /*뒤로가기*/
                ClickableIcon(Icons.Default.ArrowBack, "Back", navController)
                /*텍스트*/
                TitleText(title)
                /*공백 또는 별(서버 정보)*/
                if (isServerDetailRoute) {
                    ClickableFavoriteIcon(idx = 1)
                } else {
                    EmptyIcon()
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
        ) {
            if(title === "Main") {
                TabItem("홈", "HomePage", navController, Modifier.weight(1f))
                TabItem("게임검색", "SearchPage", navController, Modifier.weight(1f))
                TabItem("관심게임", "FavoritePage", navController, Modifier.weight(1f))
                TabItem("거래순위", "RankPage", navController, Modifier.weight(1f))
            }
        }
        if (info !== "" && info != null) {
            PageInfo(info)
        }
    }
}

@Composable
fun TabItem(title: String, route: String, navController: NavHostController, modifier: Modifier) {
    TextButton(
        onClick = { navController.navigate(route) },
        modifier = modifier
            .background(Color.Gray)
    ) {
        Text(text = title, color = Color.Black)
    }
}

@Composable
fun ClickableIcon(imageVector: ImageVector, route: String, navController: NavHostController) {
    IconButton(onClick = {
        if (route === "Back") {
            navController.popBackStack()
        } else {
            navController.navigate(route)
        }
    }) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun ClickableFavoriteIcon(idx: Number) {
    var isFavorite by remember { mutableStateOf(false) }

    IconButton(onClick = {
        isFavorite = !isFavorite
    }) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Favorite",
            tint = if (isFavorite) Color.Yellow else Color.Gray,
            modifier = Modifier.size(40.dp)
        )
    }
}

/*탑 헤더의 타이틀*/
@Composable
fun TitleText(title: String) {
    Text(text = title)
}

/*탑 헤더의 우측 빈 영역*/
@Composable
fun EmptyIcon() {
    Icon(
        imageVector = Icons.Default.AddCircle,
        contentDescription = "Empty",
        tint = Color.Transparent,
    )
}

/*페이지 정보 출력용*/
@Composable
fun PageInfo(info: String) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
        Text(text = info)
    }
}