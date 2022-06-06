plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdk = Configs.COMPILE_SDK

    defaultConfig {
        applicationId = Configs.APPLICATION_ID
        minSdk = Configs.MIN_SDK
        targetSdk = Configs.TARGET_SDK
        versionCode = Configs.VERSION_CODE
        versionName = Configs.VERSION_NAME

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }
        buildConfigField("String", "PROD_API_HOST", "\"${Configs.PROD_API_HOST}\"")
        buildConfigField("String", "DEV_API_HOST", "\"${Configs.DEV_API_HOST}\"")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}
//android {
//    compileSdk 32
//
//    defaultConfig {
//        applicationId "com.dumihi.todoapp"
//        minSdk 21
//        targetSdk 32
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = '1.8'
//    }
//}

//dependencies {
//
//    implementation 'androidx.core:core-ktx:1.7.0'
//    implementation 'androidx.appcompat:appcompat:1.4.1'
//    implementation 'com.google.android.material:material:1.6.0'
//    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Java
    implementation(Libs.JAVA_ANNOTATION)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)
    implementation(Libs.COROUTINES)
//    implementation(Libs.COROUTINES)
//    'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:1.0.0'

    // Android
    implementation(Libs.ANNOTATION)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.CORE_KTX)
    implementation(Libs.MATERIAL)
    implementation(Libs.MULTI_DEX)
    implementation(Libs.NAVIGATION_FRAGMENT)
    implementation(Libs.NAVIGATION_UI)
    implementation(Libs.VIEW_PAGER_2)
    implementation(Libs.IMAGE_CROPPER)

    // 3rd-party
    // Dagger
    implementation(Libs.DAGGER)
    implementation(Libs.DAGGER_ANDROID)
    kapt(Libs.DAGGER_COMPILER)
    kapt(Libs.DAGGER_PROCESSOR)

    // Fabric and Firebase
    implementation(Libs.FIREBASE_ANALYTICS)
    implementation(Libs.FIREBASE_CRASHLYTICS)
    implementation(Libs.FIREBASE_MESSAGING)

    // Glide
    implementation(Libs.GLIDE)
    kapt(Libs.GLIDE_COMPILER)

    // Gson
    implementation(Libs.GSON)

    // OkHttp
    implementation(Libs.OK_HTTP)
    implementation(Libs.OK_HTTP_LOGGING_INTERCEPTOR)

    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_RX_ADAPTER)
    implementation(Libs.RETROFIT_GSON_CONVERTER)

//    debugImplementation(Libs.LEAK_CANARY)

    implementation(Libs.PLAY_AUTH)

    implementation(Libs.COIL)

    implementation(Libs.GUAVA)

    // Instrumentation tests
    androidTestImplementation(Libs.ESPRESSO_CORE)
    androidTestImplementation(Libs.ANDROID_RUNNER)

    // Local unit tests
    testImplementation(Libs.JUNIT)

    // Compose UI Tests
    implementation(Libs.COMPOSE_JUNIT)
}