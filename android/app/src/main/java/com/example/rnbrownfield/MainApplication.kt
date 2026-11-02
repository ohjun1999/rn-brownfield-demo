package com.example.rnbrownfield

import android.app.Application
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeApplicationEntryPoint.loadReactNative
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost

class MainApplication : Application(), ReactApplication {

    // lazy: RN 화면을 처음 열 때 JS 런타임이 만들어진다. 네이티브만 쓰는 사용자는 비용을 내지 않는다.
    override val reactHost: ReactHost by lazy {
        getDefaultReactHost(
            context = applicationContext,
            packageList = PackageList(this).packages.apply {
            },
        )
    }

    override fun onCreate() {
        super.onCreate()
        // SoLoader, New Architecture 플래그 등 RN 네이티브 라이브러리 초기화 (JS 실행은 하지 않음)
        loadReactNative(this)
    }
}
