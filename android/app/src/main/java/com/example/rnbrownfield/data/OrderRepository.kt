package com.example.rnbrownfield.data

/** 실제 앱이라면 API/DB에서 가져올 주문 목록. 데모에서는 고정 데이터를 사용한다. */
object OrderRepository {
    fun getOrders(): List<Order> = listOf(
        Order("A-1001", "무선 키보드", 1, 59_000, OrderStatus.DELIVERED),
        Order("A-1002", "USB-C 케이블", 3, 9_900, OrderStatus.SHIPPING),
        Order("A-1003", "27인치 모니터", 1, 329_000, OrderStatus.PENDING),
        Order("A-1004", "노트북 거치대", 2, 34_500, OrderStatus.SHIPPING),
    )
}
