package com.ngga_ring.aplikasimobileappportfolio.ui.view

import android.graphics.Paint
import android.graphics.fonts.Font
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DonutChartData(
    val label: String,
    val percentage: String,
    val data: List<DonutChartTransactionData>
)

data class DonutChartTransactionData(
    val trx_date: String,
    val nominal: Int
)



@Composable
fun DonutChartView(data: List<DonutChartData>,  onItemClick: () -> Unit) {
    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow
        // Add more colors if needed
    )

    Column(
        modifier = Modifier.fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(16.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val center = Offset(
                x = canvasWidth / 2,
                y = canvasHeight / 2
            )

            val radius = canvasWidth / 4

            var startAngle = 0f
            var sweepAngle = 0f

            val paint = Paint().apply {
                style = Paint.Style.STROKE
                strokeWidth = radius * 0.8f
            }

            val textPaint = Paint().apply {
                style = Paint.Style.FILL
                textAlign = Paint.Align.CENTER
                textSize = 16.sp.toPx()
            }

            data.forEachIndexed { index, chartData ->
                val textname = chartData.label
                val percentage = chartData.percentage.toFloat()
                sweepAngle = percentage / 100 * 360

                paint.shader = LinearGradientShader(
                    from = center,
                    to = center,
                    colors = listOf(colors[index % colors.size], colors[index % colors.size])
                )

                drawArc(
                    color = colors[index % colors.size],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(16f)
                )

                val textX =
                    center.x + (radius * 1.5f) * Math.cos(Math.toRadians((startAngle + sweepAngle / 2).toDouble()))
                        .toFloat()
                val textY =
                    center.y + (radius * 1.5f) * Math.sin(Math.toRadians((startAngle + sweepAngle / 2).toDouble()))
                        .toFloat()

                drawIntoCanvas {
                    it.nativeCanvas.drawText(
                        chartData.label,
                        textX,
                        textY,
                        textPaint
                    )
                }


                startAngle += sweepAngle
            }
        }
    }
}


@Preview
@Composable
fun PreviewDonutChartView() {
    val data = listOf(
        DonutChartData(
            label = "Tarik Tunai",
            percentage = "55",
            data = listOf(
                DonutChartTransactionData("21/01/2023", 1000000),
                DonutChartTransactionData("20/01/2023", 500000),
                DonutChartTransactionData("19/01/2023", 1000000)
            )
        ),
        DonutChartData(
            label = "QRIS Payment",
            percentage = "31",
            data = listOf(
                DonutChartTransactionData("21/01/2023", 159000),
                DonutChartTransactionData("20/01/2023", 35000),
                DonutChartTransactionData("19/01/2023", 1500)
            )
        ),
        DonutChartData(
            label = "Topup Gopay",
            percentage = "7.7",
            data = listOf(
                DonutChartTransactionData("21/01/2023", 200000),
                DonutChartTransactionData("20/01/2023", 195000),
                DonutChartTransactionData("19/01/2023", 5000000)
            )
        ),
        DonutChartData(
            label = "Lainnya",
            percentage = "6.3",
            data = listOf(
                DonutChartTransactionData("21/01/2023", 1000000),
                DonutChartTransactionData("20/01/2023", 500000),
                DonutChartTransactionData("19/01/2023", 1000000)
            )
        )
    )

    DonutChartView(data = data){}
}
