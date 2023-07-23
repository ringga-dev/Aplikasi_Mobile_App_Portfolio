package com.ngga_ring.aplikasimobileappportfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.ngga_ring.aplikasimobileappportfolio.ui.theme.AplikasiMobileAppPortfolioTheme
import com.ngga_ring.aplikasimobileappportfolio.ui.view.DonutChartData

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiMobileAppPortfolioTheme {
                // Get the JSON data from the intent
                val selectedItemJson = intent.getStringExtra("selectedItem")

                // Convert JSON to Item object
                val selectedItem = Gson().fromJson(selectedItemJson, DonutChartData::class.java)

                showData(selectedItem)
            }
        }
    }
}

@Composable
fun showData(data: DonutChartData) {

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "${data.label ?: "N/A"}", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "percentage: ${data.percentage ?: "N/A"}", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Riwayat}", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            data.data.forEachIndexed { index, item ->
                Text(text = "date: ${item.trx_date ?: "N/A"}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Rp ${item.nominal ?: "N/A"}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}




