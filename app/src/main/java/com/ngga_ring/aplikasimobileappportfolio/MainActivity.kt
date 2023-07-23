package com.ngga_ring.aplikasimobileappportfolio

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.print.PrintAttributes.Margins
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.gson.Gson
import com.ngga_ring.aplikasimobileappportfolio.ui.theme.AplikasiMobileAppPortfolioTheme
import com.ngga_ring.aplikasimobileappportfolio.ui.view.DonutChartData
import com.ngga_ring.aplikasimobileappportfolio.ui.view.DonutChartTransactionData
import com.ngga_ring.aplikasimobileappportfolio.ui.view.DonutChartView
import com.ngga_ring.aplikasimobileappportfolio.ui.view.LineChart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiMobileAppPortfolioTheme {
                // A surface container using the 'background' color from the theme
                var chart by remember {
                    mutableStateOf(true)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            if (chart) {
                                LineChart(month = listOf(3, 7, 8, 10, 5, 10, 1, 3, 5, 10, 7, 7))
                            } else {
                                var showDialog by remember { mutableStateOf(false) }
                                Column {
                                    DonutChartView(datachart) { showDialog = true }
                                    if (showDialog) {
                                        Surface(
                                            modifier = Modifier.padding(8.dp),
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            LazyColumn(
                                                modifier = Modifier.fillMaxSize(),
                                                content = {

                                                    this.items(datachart) { item ->
                                                        Surface(
                                                            modifier = Modifier
                                                                .padding(8.dp)
                                                                .fillMaxWidth(),
                                                            shape = RoundedCornerShape(8.dp),
                                                            color = Color.Gray
                                                        ) {

                                                            Column(Modifier.clickable {
                                                                // Handle item click here
                                                                val i = Intent(
                                                                    this@MainActivity,
                                                                    PaymentActivity::class.java
                                                                )
                                                                i.putExtra(
                                                                    "selectedItem",
                                                                    item.toJson()
                                                                )
                                                                startActivity(i)
                                                                showDialog = false
                                                            }.padding(16.dp)) {
                                                                Text(
                                                                    text = "${item.label ?: "N/A"}",
                                                                    fontSize = 18.sp
                                                                )
                                                                Spacer(modifier = Modifier.height(8.dp))
                                                                Text(
                                                                    text = "${item.percentage ?: "N/A"}",
                                                                    fontSize = 18.sp
                                                                )
                                                                Spacer(modifier = Modifier.height(8.dp))
                                                            }
                                                        }

                                                    }

                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }


                        AddButton { chart = !chart }
                    }

                }
            }
        }
    }
}


val datachart = listOf(
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
val colors = listOf(
    Color.Red,
    Color.Green,
    Color.Blue,
    Color.Yellow
    // Add more colors if needed
)


@Composable
fun AddButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Button(
            onClick = {
                onClick()
            },
        ) {
            Text(text = "Change", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

fun Any.toJson(): String = Gson().toJson(this)
