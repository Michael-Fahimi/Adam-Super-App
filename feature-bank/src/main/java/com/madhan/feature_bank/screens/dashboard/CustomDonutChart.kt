package com.madhan.feature_bank.screens.dashboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.madhan.feature_bank.R


@Composable
fun CustomDonutChart() {
    var selectedSlice by remember { mutableStateOf<PieChartData.Slice?>(null) }

    val fillIcons = listOf(
        R.drawable.home_fill,
        R.drawable.food_fill,
        R.drawable.move_fill,
        R.drawable.shop_fill,
        R.drawable.misc_fill
    )

    val donutChartConfig = PieChartConfig(
        showSliceLabels = false,
        strokeWidth = 120f,
        activeSliceAlpha = .9f,
        isAnimationEnable = true,
        labelVisible = false,
        chartPadding = 25,
    )

    val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("Home", 200.32f, Color(0xFF017DFF)),
            PieChartData.Slice("Food", 234.22f, Color(0xFFFF7A1A)),
            PieChartData.Slice("Move", 567.43f, Color(0xFF10C971)),
            PieChartData.Slice("Shop", 180.38f, Color(0xFF6C63FF)),
            PieChartData.Slice("Misc", 467.2f, Color(0xFFFFC10E))
        ),
        plotType = PlotType.Donut
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        DonutPieChart(
            modifier = Modifier.fillMaxSize(),
            donutChartData,
            donutChartConfig
        ) { slice ->
            selectedSlice = slice
        }

        selectedSlice?.let { slice ->
            val index = donutChartData.slices.indexOfFirst { it.label == slice.label }
            val iconResId = if (index in fillIcons.indices) fillIcons[index] else null

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                iconResId?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = slice.label,
                        modifier = Modifier.size(40.dp),
                    )
                }
                Text(
                    text = "$${slice.value}",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = slice.label,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomDonutChartPreview(){
    CustomDonutChart()
}