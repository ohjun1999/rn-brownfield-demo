package com.example.rnbrownfield.rn

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

/** 앱 내부 TurboModule 목록. 모듈은 JS 에서 처음 접근할 때 lazy 하게 생성된다. */
class AppNativePackage : BaseReactPackage() {

    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? =
        when (name) {
            DeviceBridgeModule.NAME -> DeviceBridgeModule(reactContext)
            else -> null
        }

    override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
        mapOf(
            DeviceBridgeModule.NAME to ReactModuleInfo(
                name = DeviceBridgeModule.NAME,
                className = DeviceBridgeModule::class.java.name,
                canOverrideExistingModule = false,
                needsEagerInit = false,
                isCxxModule = false,
                isTurboModule = true,
            ),
        )
    }
}
