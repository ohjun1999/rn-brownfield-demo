package com.example.rnbrownfield.rn

import android.os.Bundle
import com.example.rnbrownfield.data.Order

/**
 * 네이티브 모델 → RN initialProps 변환.
 * Bundle 안의 List<Bundle> 은 RN(Arguments.fromBundle)에서 JS 객체 배열로 변환된다.
 * 키 이름은 src/types.ts 의 OrdersAppProps 와 반드시 일치해야 한다.
 */
object OrderPropsMapper {
    fun toInitialProps(orders: List<Order>, userName: String): Bundle = Bundle().apply {
        putString("userName", userName)
        putParcelableArrayList("orders", ArrayList(orders.map { it.toBundle() }))
    }

    private fun Order.toBundle(): Bundle = Bundle().apply {
        putString("id", id)
        putString("productName", productName)
        putInt("quantity", quantity)
        putInt("price", price)
        putString("status", status.name)
    }
}
