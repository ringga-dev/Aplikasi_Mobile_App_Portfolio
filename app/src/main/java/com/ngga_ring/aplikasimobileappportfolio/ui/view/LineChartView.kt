package com.ngga_ring.aplikasimobileappportfolio.ui.view


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LineChart(month: List<Int>) {
    var maxValue by remember { mutableStateOf(0) }

    // Cari nilai maksimal dari data bulan
    LaunchedEffect(month) {
        maxValue = month.maxOrNull() ?: 0
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                val width = size.width
                val height = size.height
                val stepX = width / (month.size - 1)
                val stepY = height / maxValue

                // Garis bantu untuk sumbu x (bulan)
                for (i in 0 until month.size) {
                    val x = i * stepX
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(x, 0f),
                        end = Offset(x, height),
                        strokeWidth = 1f
                    )
                }

                // Garis bantu untuk sumbu y (nilai)
                for (i in 0..maxValue) {
                    val y = height - i * stepY
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, y),
                        end = Offset(width, y),
                        strokeWidth = 1f
                    )
                }

                // Garis data
                month.forEachIndexed { index, value ->
                    val x = index * stepX
                    val y = height - value * stepY
                    drawCircle(
                        color = Color.Blue,
                        center = Offset(x, y),
                        radius = 4f
                    )

                    if (index < month.size - 1) {
                        val nextX = (index + 1) * stepX
                        val nextY = height - month[index + 1] * stepY
                        drawLine(
                            color = Color.Blue,
                            start = Offset(x, y),
                            end = Offset(nextX, nextY),
                            strokeWidth = 2f
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun LineChartPreview() {
    MaterialTheme {
        LineChart(month = listOf(3, 7, 8, 10, 5, 10, 1, 3, 5, 10, 7, 7))
    }
}



