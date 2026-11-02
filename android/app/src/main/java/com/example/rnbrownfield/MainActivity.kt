package com.example.rnbrownfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rnbrownfield.data.OrderRepository
import com.example.rnbrownfield.rn.OrdersReactActivity
import com.example.rnbrownfield.ui.HomeScreen
import com.example.rnbrownfield.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val orders = OrderRepository.getOrders()
        setContent {
            AppTheme {
                HomeScreen(
                    orders = orders,
                    onOpenReactNative = {
                        startActivity(OrdersReactActivity.newIntent(this, orders, userName = "홍길동"))
                    },
                )
            }
        }
    }
}
