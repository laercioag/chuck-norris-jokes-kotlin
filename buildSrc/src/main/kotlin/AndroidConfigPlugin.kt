import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

open class AndroidConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configureAndroid()
    }
}

internal fun Project.configureAndroid() = extensions.getByType<BaseAppModuleExtension>().run {
    buildToolsVersion = Versions.buildToolsVersion
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        applicationId = App.applicationId
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = App.versionCode
        versionName = App.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }

        getByName("debug") {
            java.srcDir("src/debug/kotlin")
        }

        getByName("test") {
            java.srcDir("src/test/kotlin")
            resources.srcDir("src/test/resources")
        }

        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }
}