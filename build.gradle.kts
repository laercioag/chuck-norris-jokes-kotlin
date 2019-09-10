buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Classpaths.gradlePlugin)
        classpath(Classpaths.kotlinPlugin)
        classpath(Classpaths.jetfier)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.create<Delete>("clean") {
    delete("build")
}

apply {
    from("detekt.gradle")
    from("ktlint.gradle")
}
