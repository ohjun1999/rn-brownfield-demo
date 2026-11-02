package com.example.rnbrownfield.rn

import android.os.Build
import android.widget.Toast
import com.example.rnbrownfield.specs.NativeDeviceBridgeSpec
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.UiThreadUtil

/**
 * src/specs/NativeDeviceBridge.ts 로부터 codegen 이 만든 NativeDeviceBridgeSpec 을 구현한다.
 * 메서드 시그니처가 TS 스펙과 어긋나면 컴파일 에러가 나므로 JS ↔ 네이티브 계약이 타입으로 보장된다.
 */
class DeviceBridgeModule(reactContext: ReactApplicationContext) : NativeDeviceBridgeSpec(reactContext) {

    override fun showToast(message: String, isLong: Boolean) {
        // TurboModule 메서드는 JS 스레드에서 호출되므로 UI 작업은 메인 스레드로 넘긴다.
        UiThreadUtil.runOnUiThread {
            Toast.makeText(
                reactApplicationContext,
                message,
                if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT,
            ).show()
        }
    }

    override fun getDeviceInfo(promise: Promise) {
        try {
            val context = reactApplicationContext
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            promise.resolve(
                Arguments.createMap().apply {
                    putString("manufacturer", Build.MANUFACTURER)
                    putString("model", Build.MODEL)
                    putString("osVersion", Build.VERSION.RELEASE)
                    putInt("sdkInt", Build.VERSION.SDK_INT)
                    putString("appVersion", packageInfo.versionName ?: "unknown")
                },
            )
        } catch (e: Exception) {
            promise.reject("E_DEVICE_INFO", e)
        }
    }

    companion object {
        const val NAME = NativeDeviceBridgeSpec.NAME
    }
}
