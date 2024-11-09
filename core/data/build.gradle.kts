import app.cash.sqldelight.core.capitalize
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.bruno13palhano.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.coroutines.test)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.paging.test)
    androidTestImplementation(libs.hilt.android.testing)
    implementation(libs.coroutines.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.android)
    implementation(libs.android.driver)
    implementation(libs.coroutines.extensions)
    implementation(libs.paging.runtime)
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.bruno13palhano.cache")
        }
    }
}

tasks.getByName("preBuild").dependsOn(":core:data:generateSqlDelightInterface")

androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val variantName = variant.name.capitalize()
            tasks.getByName<KotlinCompile>("ksp${variantName}Kotlin") {
                setSource(tasks.getByName("generate${variantName}AppDatabaseInterface").outputs)
            }
        }
    }
}