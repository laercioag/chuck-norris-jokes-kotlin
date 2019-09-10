object Versions {

    const val buildToolsVersion = "28.0.3"
    const val compileSdkVersion = 28
    const val minSdkVersion = 16
    const val targetSdkVersion = 28

    const val gradlePluginVersion = "3.5.0"
    const val kotlinVersion = "1.3.50"
    const val jetfierVersion = "1.0.0-beta02"

    const val appCompatVersion = "1.0.2"
    const val materialVersion = "1.0.0-rc01"
    const val constraintLayoutVersion = "2.0.0-alpha2"
    const val recyclerViewVersion = "1.0.0"
    const val androidKtxVersion = "1.0.0-alpha1"
    const val gsonVersion = "2.8.5"
    const val okhttpVersion = "3.12.0"
    const val retrofitVersion = "2.5.0"
    const val rxAndroidVersion = "2.1.0"
    const val rxJavaVersion = "2.2.4"
    const val daggerVersion = "2.19"
    const val glideVersion = "4.8.0"
    const val browserVersion = "1.0.0"

    const val jUnitVersion = "4.12"
    const val mockitoVersion = "2.23.4"
    const val mockWebServerVersion = "3.12.0"

    const val testRunnerVersion = "1.1.1-alpha01"
    const val espressoCoreVersion = "3.1.1-alpha01"
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
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    val androidKtx = "androidx.core:core-ktx:${Versions.androidKtxVersion}"
    val androidKtxFragment = "androidx.fragment:fragment-ktx:${Versions.androidKtxVersion}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val browser = "androidx.browser:browser:${Versions.browserVersion}"
}

object KaptDependencies {
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    val daggerAndroidCompiler =
        "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
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