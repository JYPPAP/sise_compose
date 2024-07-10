package com.mania.mania_sise_240523

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.height

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.AccessibilityConfig
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.drawBarGraph

import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ServerDetail(serverName: String) {
    var isTable by remember { mutableStateOf(true) }
    val priceInfoList = generatePriceInfoList()

    Column {
        Row(
            modifier = Modifier
                .height(40.dp)
                .border(2.dp, Color.Cyan, shape = RoundedCornerShape(16.dp))
        ) {
            tabButton(text = "표 보기", Modifier.weight(1f)) {
                isTable = true
            }
            tabButton(text = "그래프 보기", Modifier.weight(1f)) {
                isTable = false
            }
        }
        if (isTable) {
            TableList(priceInfoList = priceInfoList)
        } else {
            GraphList(priceInfoList = priceInfoList)
        }
    }
}

@Composable
fun tabButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(text = text, color = Color.Black)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun generatePriceInfoList(): List<PriceInfo> {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val today = LocalDate.now()

    return List(16) { index ->
        val date = today.minusDays(index.toLong()).format(dateFormatter)
        PriceInfo(date = date, averagePrice = 1890 + index * 10, fluctuation = index * 2 - 10)
    }
}


@Composable
fun TableList(priceInfoList: List<PriceInfo>) {
    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {
                Text(
                    text = "날짜",
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
            priceInfoList.forEach { priceInfo ->
                InfoLinkItem(priceInfo)
            }
        }
    }
}

@Composable
fun InfoLinkItem(priceInfo: PriceInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "${priceInfo.date}",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(5f)
        )
        Text(
            text = "${priceInfo.averagePrice}",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(3f)
        )
        Row(
            modifier = Modifier.weight(2f)
        ) {
            if (priceInfo.fluctuation > 0) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription ="UP", tint = Color.Red )
            } else if (priceInfo.fluctuation < 0) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription ="DOWN", tint = Color.Blue )
            } else {
                Icon(imageVector = Icons.Default.Menu, contentDescription ="-", tint = Color.Black )
            }
            Text(
                text = "${priceInfo.fluctuation}",
                textAlign = TextAlign.Center,
            )
        }
    }
}

data class PriceInfo(
    val date: String,
    val averagePrice: Int,
    val fluctuation: Int
)


/* #################################### Chart #################################### */
@Composable
fun GraphList(priceInfoList: List<PriceInfo>) {
    val maxRange = priceInfoList[0].averagePrice
    val barData = DataUtils.getBarChartData(priceInfoList.size, maxRange, BarChartType.VERTICAL, DataCategoryOptions())
    val yStepSize = 5

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .startDrawPadding(25.dp)
        .labelData { index ->
            if (index % 2 == 0) {
                priceInfoList[index].date.substringAfter("2024-")
            } else {
                "" // 홀수 인덱스인 경우 빈 문자열 반환
            }
        }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(4.dp)
        .axisOffset(20.dp)
        .labelData { index -> priceInfoList[index].averagePrice.toString() }
        .build()

    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            paddingBetweenBars = 0.dp,
            barWidth = 25.dp,
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp,
    )

    BarChart(modifier = Modifier.height(700.dp).background(Color.Red), barChartData = barChartData)
}