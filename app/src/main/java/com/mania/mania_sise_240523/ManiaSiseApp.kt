// ManiaSiseApp.kt
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.airbnb.lottie.model.content.CircleShape
import com.mania.mania_sise_240523.AppHeader
import com.mania.mania_sise_240523.FavoritePage
import com.mania.mania_sise_240523.HomePage
import com.mania.mania_sise_240523.RankPage
import com.mania.mania_sise_240523.SearchPage
import com.mania.mania_sise_240523.ServerDetail
import com.mania.mania_sise_240523.ServerList
import com.mania.mania_sise_240523.SettingPage
import com.mania.mania_sise_240523.SplashScreen
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Build
import com.mania.mania_sise_240523.AddServer
import com.mania.mania_sise_240523.GameList
import com.mania.mania_sise_240523.PolicyPage

import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ManiaSiseApp() {
    val navController = rememberNavController()
    val currentRoute = currentRoute(navController)

    val currentDate = Date() // 현재 날짜와 시간을 가져옵니다.
    val dateFormat = SimpleDateFormat("YYYY년 MM월 dd일 순위", Locale.KOREA) // 원하는 형식의 SimpleDateFormat 객체를 생성합니다.
    val formattedDate = dateFormat.format(currentDate) // 현재 날짜를 지정된 형식으로 변환합니다.

    Scaffold(
        topBar = {
            val currentBackStackEntry = navController.currentBackStackEntry
            val currentRoute = currentBackStackEntry?.destination?.route

            when {
                currentRoute == "HomePage" -> AppHeader("Main", "대표게임", navController)
                currentRoute == "SearchPage" -> AppHeader("Main","게임검색", navController)
                currentRoute == "FavoritePage" -> AppHeader("Main","관심게임", navController)
                currentRoute == "RankPage" -> AppHeader("Main",formattedDate, navController)
                currentRoute?.startsWith("ServerList/") == true -> {
                    val gameName = currentBackStackEntry?.arguments?.getString("gameName") ?: ""
                    Log.d("Route Check", "gameName: $gameName")
                    AppHeader(gameName,"기준 : 100일 다이아", navController)
                }
                currentRoute?.startsWith("ServerDetail/") == true -> {
                    val serverName = currentBackStackEntry?.arguments?.getString("serverName") ?: ""
                    Log.d("Route Check", "serverName: $serverName")
                    AppHeader(serverName, "기준 : 100일 다이아", navController)
                }
                currentRoute == "GameList" -> AppHeader("추가하기","", navController)
                currentRoute?.startsWith("AddServer/") == true -> {
                    AppHeader("추가하기", "", navController)
                }
                currentRoute == "SettingPage" -> AppHeader("설정","", navController)
                currentRoute == "PolicyPage" -> AppHeader("개인정보처리방침","", navController)
                currentRoute == "Splash" -> {}
            }
        },
        floatingActionButton = {
            when {
                currentRoute == "FavoritePage" -> FloatingActionButtonWithOptions(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("Splash") {
                SplashScreen(navController = navController)
            }
            composable("HomePage") {
                HomePage(navController = navController)
            }
            composable("SearchPage") {
                SearchPage(navController = navController)
            }
            composable("FavoritePage") {
                FavoritePage(navController = navController)
            }
            composable("RankPage") {
                RankPage(navController = navController)
            }
            composable("GameList") {
                GameList(navController = navController)
            }
            composable("PolicyPage") {
                PolicyPage()
            }
            composable(
                route = "AddServer/{gameName}",
                arguments = listOf(navArgument("gameName") { type = androidx.navigation.NavType.StringType })
            ) { backStackEntry ->
                val gameName = backStackEntry.arguments?.getString("gameName") ?: ""
                AddServer(gameName, navController)
            }
            composable("SettingPage") {
                SettingPage(navController = navController)
            }
            composable(
                route = "ServerList/{gameName}",
                arguments = listOf(navArgument("gameName") { type = androidx.navigation.NavType.StringType })
            ) { backStackEntry ->
                val gameName = backStackEntry.arguments?.getString("gameName") ?: ""
                Log.d("Host Check", "gameName: ${gameName}")
                ServerList(gameName, navController)
            }
            composable(
                route = "ServerDetail/{serverName}",
                arguments = listOf(navArgument("serverName") { type = androidx.navigation.NavType.StringType })
            ) { backStackEntry ->
                val serverName = backStackEntry.arguments?.getString("serverName") ?: ""
                Log.d("Host Check", "serverName: ${serverName}")
                ServerDetail(serverName = serverName)
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun FloatingActionButtonWithOptions(navController: NavHostController) {
    var isExpanded by remember { mutableStateOf(false) }
    var rotationAngle by remember { mutableStateOf(0f) }

    Column(
        horizontalAlignment = Alignment.End // 추가/편집 버튼을 오른쪽 정렬
    ) {
        if (isExpanded) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Button(
                    onClick = {
                        navController.navigate("GameList")
                    },
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp)),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "추가"
                        )
                        Text("추가하기")
                    }
                }
                Button(
                    onClick = {/*onEditModeClick*/},
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp)),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Build,
                            contentDescription = "편집"
                        )
                        Text("편집하기")
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                isExpanded = !isExpanded
                rotationAngle = if (isExpanded) 45f else 0f
            },
            modifier = Modifier.rotate(rotationAngle),
            containerColor = Color.Blue, // 배경색 설정
            shape = CircleShape,
            contentColor = Color.Black // 아이콘 색상
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                tint = Color.White,
                contentDescription = if (isExpanded) "닫기" else "열기"
            )
        }
    }
}