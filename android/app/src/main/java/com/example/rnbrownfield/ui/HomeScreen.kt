package com.example.rnbrownfield.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rnbrownfield.data.Order
import com.example.rnbrownfield.data.OrderRepository
import com.example.rnbrownfield.ui.theme.AppTheme
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(orders: List<Order>, onOpenReactNative: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("네이티브 홈 (Compose)") }) },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text("최근 주문 ${orders.size}건", style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(orders, key = { it.id }) { OrderRow(it) }
            }
            Button(onClick = onOpenReactNative, modifier = Modifier.fillMaxWidth()) {
                Text("주문 상세 보기 (React Native)")
            }
        }
    }
}

@Composable
private fun OrderRow(order: Order) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text("${order.productName} × ${order.quantity}", style = MaterialTheme.typography.bodyLarge)
            Text(
                "${order.id} · ${formatWon(order.price * order.quantity)} · ${order.status}",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

private fun formatWon(amount: Int): String =
    NumberFormat.getNumberInstance(Locale.KOREA).format(amount) + "원"

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme { HomeScreen(OrderRepository.getOrders(), onOpenReactNative = {}) }
}
