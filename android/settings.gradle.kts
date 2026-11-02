pluginManagement {
    // RN gradle plugin은 node_modules에서 소스로 포함된다 (RN 버전과 플러그인 버전이 항상 일치하도록).
    includeBuild("../node_modules/@react-native/gradle-plugin")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.facebook.react.settings")
}

// node_modules의 RN 라이브러리를 자동으로 :app 에 링크 (npx react-native config 결과 사용)
extensions.configure<com.facebook.react.ReactSettingsExtension> {
    autolinkLibrariesFromCommand()
}

rootProject.name = "RNBrownfieldDemo"
include(":app")
includeBuild("../node_modules/@react-native/gradle-plugin")
