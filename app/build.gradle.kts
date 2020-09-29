plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions{
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }

    viewBinding { isEnabled = true }
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation(SupportLibs.ANDROIDX_APPCOMPAT)
    implementation(SupportLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(SupportLibs.CARD_VIEW)
    implementation(SupportLibs.RECYCLER_VIEW)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")


    testImplementation(TestingLib.JUNIT)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.ESPRESSO_CORE)

    api(KOTLIN_REFLECT.KOTLIN_REFLECT)

    implementation(RETROFIT.RETROFIT)
    implementation(RETROFIT.RETROFIT_JSON_CONVERTER)
    implementation(RETROFIT.RETROFIT_MOSHI_CONVERTER)


    implementation(LIFECYCLE.REACTIVE_STREAMS)
    implementation(LIFECYCLE.LIFECYCLE)
    kapt(LIFECYCLE.LIFECYCLE_KAPT)

    implementation(DAGGER.DAGGER)
    kapt(DAGGER.DAGGER_KAPT)
    implementation(DAGGER.DAGGER_ANDROID)
    kapt(DAGGER.DAGGER_ANNOTATION)

    implementation(NAVIGATION.NAVIGATION_FRAGMENT)
    implementation(NAVIGATION.NAVIGATION_UI)
    implementation(NAVIGATION.NAVIGATION_RUNTIME)

    implementation(PAGING.PAGING_RUNTIME)
    implementation(PAGING.PAGING_COMMON)
    implementation(PAGING.PAGING_RX)

    implementation(MATERIAL.MATERIAL)

    implementation(LOGGING_INTERCEPTORS.LOGGING_INTERCEPTORS)

    implementation(TIMBER.TIMBER)

    implementation(RX.RXANDROID)
    implementation(RX.RXJAVA)
    implementation(RX.RETROFIT)

    implementation(GLIDE.GLIDE)
    annotationProcessor(GLIDE.GLIDE_COMPILER)

    implementation(COIL.COIL)

}
