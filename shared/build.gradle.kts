plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.muabdz.shared"
    compileSdk = AndroidProjectConfig.compileSdk

    defaultConfig {
        minSdk = AndroidProjectConfig.minSdk
        targetSdk = AndroidProjectConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    //chucker
    debugImplementation(Libraries.chucker)
//    debugImplementation(Libraries.chuckerNoOp)

    //core module
    api(project(":core"))
}