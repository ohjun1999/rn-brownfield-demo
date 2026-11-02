package com.example.rnbrownfield.data

enum class OrderStatus { PENDING, SHIPPING, DELIVERED }

data class Order(
    val id: String,
    val productName: String,
    val quantity: Int,
    val price: Int,
    val status: OrderStatus,
)
