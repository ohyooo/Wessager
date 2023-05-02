plugins {
    id("com.android.application") version Libs.Versions.agp apply false
    id("org.jetbrains.kotlin.android") version Libs.Versions.kotlin apply false
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn", "-Xbackend-threads=12", "-Xcontext-receivers", "-jvm-target=17")
            jvmTarget = "17"
        }
    }
}
