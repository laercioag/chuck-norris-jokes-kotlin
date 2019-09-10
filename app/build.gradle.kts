plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("android-config-plugin")
}

tasks {
    "preBuild" {
        dependsOn(":detekt")
        dependsOn(":ktlint")
    }
}

val androidTestImplementation by configurations
dependencies {
    implementation(Dependencies.kotlin)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.androidKtx)
    implementation(Dependencies.androidKtxFragment)
    implementation(Dependencies.gson)
    implementation(Dependencies.rxJava)
    implementation(Dependencies.rxAndroid)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttpLogging)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.retrofitRxJava)
    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.glide)
    implementation(Dependencies.browser)

    kapt(KaptDependencies.daggerCompiler)
    kapt(KaptDependencies.daggerAndroidCompiler)
    kapt(KaptDependencies.glideCompiler)

    testImplementation(TestDependencies.jUnit)
    testImplementation(TestDependencies.mocikto)
    testImplementation(TestDependencies.mociktoInline)
    testImplementation(TestDependencies.mockWebServer)

    androidTestImplementation(AndroidTestDependencies.testRunner)
    androidTestImplementation(AndroidTestDependencies.espressoCore)
}
