object Versions {

    val buildToolsVersion = "28.0.3"
    val compileSdkVersion = 28
    val minSdkVersion = 16
    val targetSdkVersion = 28

    val gradlePluginVersion = "3.2.1"
    val kotlinVersion = "1.3.10"
    val jetfierVersion = "1.0.0-beta02"

    val appCompatVersion = "1.0.2"
    val materialVersion = "1.0.0-rc01"
    val constraintLayoutVersion = "2.0.0-alpha2"
    val recyclerViewVersion = "1.0.0"
    val androidKtxVersion = "1.0.0-alpha1"
    val gsonVersion = "2.8.5"
    val retrofitVersion = "2.5.0"
    val rxAndroidVersion = "2.1.0"
    val rxJavaVersion = "2.2.4"
    val daggerVersion = "2.19"
    val glideVersion = "4.8.0"

    val jUnitVersion = "4.12"
    val mockitoVersion = "2.23.4"
    val mockWebServerVersion = "3.12.0"

    val testRunnerVersion = "1.1.1-alpha01"
    val espressoCoreVersion = "3.1.1-alpha01"
}

object Classpaths {
    val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePluginVersion}"
    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val jetfier = "com.android.tools.build.jetifier:jetifier-processor:${Versions.jetfierVersion}"
}

object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    val material = "com.google.android.material:material:${Versions.materialVersion}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    val androidKtx = "androidx.core:core-ktx:${Versions.androidKtxVersion}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
}

object KaptDependencies {
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
}

object TestDependencies {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val mocikto = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    val mociktoInline = "org.mockito:mockito-inline:${Versions.mockitoVersion}"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServerVersion}"
}

object AndroidTestDependencies {
    val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}