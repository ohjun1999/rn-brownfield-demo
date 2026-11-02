package com.example.rnbrownfield

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rnbrownfield.data.OrderRepository
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
                        Toast.makeText(this, "RN 연동 전입니다", Toast.LENGTH_SHORT).show()
                    },
                )
            }
        }
    }
}
