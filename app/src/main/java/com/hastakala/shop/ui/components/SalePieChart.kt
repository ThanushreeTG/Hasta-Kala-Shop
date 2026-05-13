package com.hastakala.shop.ui.components

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.hastakala.shop.data.ColorStat

/**
 * Single MPAndroidChart [PieChart] wrapper used across Home and Analytics to avoid duplicated
 * chart setup and inconsistent behavior.
 */
@Composable
fun SalePieChart(
    colorStats: List<ColorStat>,
    totalCount: Int,
    modifier: Modifier = Modifier,
    usePercentValues: Boolean = true,
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp),
        factory = { context ->
            PieChart(context).apply {
                description.isEnabled = false
                legend.isEnabled = true
                legend.textSize = 12f
                setHoleColor(AndroidColor.TRANSPARENT)
                setTransparentCircleAlpha(0)
                holeRadius = 56f
                transparentCircleRadius = 58f
                setDrawCenterText(true)
                setCenterTextSize(16f)
                setEntryLabelTextSize(11f)
                setUsePercentValues(usePercentValues)
                setDrawEntryLabels(true)
                setExtraOffsets(8f, 10f, 8f, 8f)
                // Rotation/drag on the chart competes with parent vertical scrolling in Compose.
                isRotationEnabled = false
                isHighlightPerTapEnabled = true
            }
        },
        update = { chart ->
            if (colorStats.isEmpty()) {
                chart.clear()
                chart.setNoDataText("No sales in this range")
                chart.setNoDataTextColor(AndroidColor.DKGRAY)
                chart.centerText = ""
                chart.invalidate()
                return@AndroidView
            }

            val entries = colorStats.map { stat ->
                val value = if (usePercentValues) stat.totalAmount.toFloat() else stat.saleCount.toFloat()
                PieEntry(value, stat.color)
            }

            val materialColors = ColorTemplate.MATERIAL_COLORS
            val colors = colorStats.indices.map { i -> materialColors[i % materialColors.size] }

            val dataSet = PieDataSet(entries, "").apply {
                this.colors = colors
                sliceSpace = 2f
                selectionShift = 4f
                valueTextSize = 11f
            }

            val data = PieData(dataSet)
            if (usePercentValues) {
                data.setValueFormatter(PercentFormatter(chart))
                data.setValueTextSize(11f)
            } else {
                data.setValueTextSize(11f)
            }

            chart.data = data
            chart.centerText = "Total\n$totalCount"
            chart.invalidate()
        },
    )
}
