// ManiaSiseApp.kt
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
//import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.mania.mania_sise_240523.AppHeader
import com.mania.mania_sise_240523.FavoritePage
import com.mania.mania_sise_240523.HomePage
import com.mania.mania_sise_240523.RankPage
import com.mania.mania_sise_240523.SearchPage
import com.mania.mania_sise_240523.ServerDetail
import com.mania.mania_sise_240523.ServerList
import com.mania.mania_sise_240523.SettingPage
import com.mania.mania_sise_240523.SplashScreen

@Composable
fun ManiaSiseApp() {
    val navController = rememberNavController()
    val currentRoute = currentRoute(navController)

    Scaffold(
        topBar = {
            val currentBackStackEntry = navController.currentBackStackEntry
            val currentRoute = currentBackStackEntry?.destination?.route

            when {
                currentRoute == "HomePage" -> AppHeader("Main", "대표게임", navController)
                currentRoute == "SearchPage" -> AppHeader("Main","", navController)
                currentRoute == "FavoritePage" -> AppHeader("Main","관심게임", navController)
                currentRoute == "RankPage" -> AppHeader("Main","2024년 06월 13일 순위", navController)
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
                currentRoute == "SettingPage" -> AppHeader("설정","", navController)
                currentRoute == "PrivatePage" -> AppHeader("개인정보처리방침","", navController)
                currentRoute == "Splash" -> {}
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
            composable(
                route = "SearchPage/{query}",
                arguments = listOf(navArgument("query") { type = NavType.StringType })
            ) { backStackEntry ->
                val query = backStackEntry.arguments?.getString("query") ?: ""
                SearchPage(navController = navController, query = query)
            }
            composable("FavoritePage") {
                FavoritePage(navController = navController)
            }
            composable("RankPage") {
                RankPage(navController = navController)
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

//
//@Preview(showBackground = true)
//@Composable
//fun AppHeaderPreview() {
//    // 임시 NavController 생성
//    val navController = rememberNavController()
//    navController.navigate("HomePage")
//    AppHeader("홈", navController)
//}