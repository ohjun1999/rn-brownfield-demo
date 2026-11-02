package com.example.rnbrownfield.rn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.rnbrownfield.data.Order
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultReactActivityDelegate

/** RN 컴포넌트 "OrdersApp" 을 띄우는 화면. 네이티브 화면과 동일하게 Intent 로 진입한다. */
class OrdersReactActivity : ReactActivity() {

    override fun getMainComponentName(): String = COMPONENT_NAME

    override fun createReactActivityDelegate(): ReactActivityDelegate =
        object : DefaultReactActivityDelegate(this, mainComponentName) {
            // 여기서 반환한 Bundle 이 RN 루트 컴포넌트의 props 가 된다.
            override fun getLaunchOptions(): Bundle? = intent.getBundleExtra(EXTRA_INITIAL_PROPS)
        }

    companion object {
        /** index.js 의 AppRegistry.registerComponent 이름과 일치해야 한다. */
        private const val COMPONENT_NAME = "OrdersApp"
        private const val EXTRA_INITIAL_PROPS = "initial_props"

        fun newIntent(context: Context, orders: List<Order>, userName: String): Intent =
            Intent(context, OrdersReactActivity::class.java)
                .putExtra(EXTRA_INITIAL_PROPS, OrderPropsMapper.toInitialProps(orders, userName))
    }
}
