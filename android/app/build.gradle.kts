plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.facebook.react")
}

react {
    // root(=RNBrownfieldDemo/)의 package.json, index.js, codegenConfig 를 기본값으로 사용한다.
    autolinkLibrariesWithApp()
}

android {
    namespace = "com.example.rnbrownfield"
    compileSdk = 37
    // 앱 레벨 TurboModule(codegen)의 JNI 코드를 빌드하기 위해 필요
    ndkVersion = "27.1.12297006"

    defaultConfig {
        applicationId = "com.example.rnbrownfield"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2026.02.01")
    implementation(composeBom)
    implementation("androidx.activity:activity-compose:1.13.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // React Native (버전은 RN gradle plugin이 node_modules/react-native 버전으로 고정)
    implementation("com.facebook.react:react-android")
    implementation("com.facebook.react:hermes-android")
}
