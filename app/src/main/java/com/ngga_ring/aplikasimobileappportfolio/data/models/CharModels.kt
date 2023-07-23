package com.ngga_ring.aplikasimobileappportfolio.data.models

data class DonutChartData(
    val type: String,
    val data: List<DonutChartDataItem>
)

data class DonutChartDataItem(
    val label: String,
    val percentage: String,
    val data: List<DonutChartTransactionData>
)

data class DonutChartTransactionData(
    val trx_date: String,
    val nominal: Int
)

data class LineChartData(
    val type: String,
    val data: LineChartDataItem
)

data class LineChartDataItem(
    val month: List<Int>
)