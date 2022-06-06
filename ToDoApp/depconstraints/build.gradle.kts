plugins {
    id("java-platform")
}


val coroutines = "1.3.4"

val javaAnnotation = "1.3.2"
val annotation = "1.1.0"
val appcompat = "1.1.0"
val billing = "3.0.0"
val constraintLayout = "2.0.4"
val core = "1.6.0"
val crashlytics = "2.9.8"
val dagger = "2.34"
val espresso = "3.1.1"
val facebook = "[9,10)"
val firebaseAnalytics = "17.4.4"
val firebaseCrashlytics = "17.1.1"
val firebaseMessaging = "21.0.0"
val fresco = "1.8.1"
val glide = "4.11.0"
val guava = "30.0-jre"
val gson = "2.8.6"
val junit = "4.13"
val leakCanary = "2.4"
val material = "1.2.1"
val mockito = "3.3.1"
val mockitoKotlin = "1.5.0"
val multidex = "2.0.1"
val navigation = "2.3.0"
val okhttp = "3.10.0"
val okHttpLoggingInterceptor = "3.8.0"
val playAuth = "18.1.0"
val runner = "1.2.0"
val viewpager2 = "1.0.0"
val workManager = "2.4.0"
val cropperImage = "2.8.+"
val coil = "1.1.1"
val retrofit = "2.9.0"
val retrofitRxAdapter = "2.1.0"
val retrofitGsonConverter = "2.3.0"
val activityCompose = "1.3.1"
val materialCompose = "1.0.1"
val animationCompose = "1.0.1"
val composeSwipeToRefresh = "0.17.0"
val composePager = "0.18.0"
val composeJUnit = "1.0.1"

dependencies {
    constraints {
        // Kotlin
        api("${Libs.KOTLIN_STDLIB}:${Versions.KOTLIN}")
        api("${Libs.COROUTINES}:$coroutines")

        // Java
        api("${Libs.JAVA_ANNOTATION}:$javaAnnotation")

        // Android
        api("${Libs.ANNOTATION}:$annotation")
        api("${Libs.APPCOMPAT}:$appcompat")
        api("${Libs.CONSTRAINT_LAYOUT}:$constraintLayout")
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.MULTI_DEX}:$multidex")
        api("${Libs.NAVIGATION_FRAGMENT}:$navigation")
        api("${Libs.NAVIGATION_UI}:$navigation")
        api("${Libs.VIEW_PAGER_2}:$viewpager2")
        api("${Libs.IMAGE_CROPPER}:$cropperImage")


        // 3rd-party
        api("${Libs.DAGGER}:$dagger")
        api("${Libs.DAGGER_ANDROID}:$dagger")
        api("${Libs.DAGGER_COMPILER}:$dagger")
        api("${Libs.DAGGER_PROCESSOR}:$dagger")

        api("${Libs.FIREBASE_ANALYTICS}:$firebaseAnalytics")
        api("${Libs.FIREBASE_CRASHLYTICS}:$firebaseCrashlytics")
        api("${Libs.FIREBASE_MESSAGING}:$firebaseMessaging")

        api("${Libs.GLIDE}:$glide")
        api("${Libs.GLIDE_COMPILER}:$glide")

        api("${Libs.GSON}:$gson")

        api("${Libs.OK_HTTP}:$okhttp")
        api("${Libs.OK_HTTP_LOGGING_INTERCEPTOR}:$okHttpLoggingInterceptor")

        api("${Libs.RETROFIT}:$retrofit")
        api("${Libs.RETROFIT_RX_ADAPTER}:$retrofitRxAdapter")
        api("${Libs.RETROFIT_GSON_CONVERTER}:$retrofitGsonConverter")

        api("${Libs.LEAK_CANARY}:$leakCanary")

        api("${Libs.PLAY_AUTH}:$playAuth")

        api("${Libs.COIL}:$coil")


        api("${Libs.GUAVA}:$guava")

        // Test
        api("${Libs.ESPRESSO_CORE}:$espresso")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.ANDROID_RUNNER}:$runner")
        api("${Libs.COMPOSE_JUNIT}:$composeJUnit")

    }
}