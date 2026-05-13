package com.hastakala.shop.ui.components

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.hastakala.shop.data.ColorStat

@Composable
fun SaleBarChart(
    colorStats: List<ColorStat>,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                legend.isEnabled = false
                axisLeft.axisMinimum = 0f
                axisRight.isEnabled = false
                xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.granularity = 1f
                axisLeft.setDrawGridLines(true)
                setFitBars(true)
                setExtraOffsets(8f, 16f, 8f, 8f)
            }
        },
        update = { chart ->
            if (colorStats.isEmpty()) {
                chart.clear()
                chart.setNoDataText("No data")
                chart.setNoDataTextColor(AndroidColor.DKGRAY)
                chart.invalidate()
                return@AndroidView
            }

            val entries = colorStats.mapIndexed { index, stat ->
                BarEntry(index.toFloat(), stat.totalAmount.toFloat())
            }

            val dataSet = BarDataSet(entries, "Revenue by color").apply {
                val materialColors = ColorTemplate.MATERIAL_COLORS
                colors = colorStats.indices.map { i -> materialColors[i % materialColors.size] }.toList()
                valueTextSize = 10f
            }

            val data = BarData(dataSet)
            data.barWidth = 0.6f

            chart.xAxis.valueFormatter =
                com.github.mikephil.charting.formatter.IndexAxisValueFormatter(colorStats.map { it.color })
            chart.xAxis.labelRotationAngle = -35f
            chart.data = data
            chart.invalidate()
        },
    )
}
