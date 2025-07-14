import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    alias(libs.plugins.androidxComposeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "de.seleri.frontend"
    compileSdk = 36

    defaultConfig {
        applicationId = "de.seleri.frontend"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            // das hier muss ersetzt werden, falls die App je in den Playstore soll:
            signingConfig = signingConfigs.getByName("debug")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    lint {
        warningsAsErrors = true
    }

    buildFeatures {
        compose = true
    }

    @Suppress("UnstableApiUsage")
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.get()
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.snakeyaml)

    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.material)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    testImplementation(libs.junit)
    testImplementation(libs.mokk)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

kover {
    reports {
        filters {
            excludes {
                classes(
                    "*ComposableSingletons*",
                    "*AppModule*",
                    "*MainActivity*",
                    "*NavigationKt",
                    "*Theme*",
                    "*ImpulseViewModel_*",
                    "de.seleri.frontend.Impulse",
                    "*Datenbanksystem\$Companion",
                    )
                packages(
                    "de.seleri.tools",
                    "dagger.hilt.internal.aggregatedroot.codegen",
                    "hilt_aggregated_deps"
                )
            }
        }
        verify {
            warningInsteadOfFailure = false

            rule("65% der Abzweigungen getestet") {
                bound {
                    minValue = 65
                    coverageUnits = CoverageUnit.BRANCH
                }
            }
            rule("70% der Anweisungen getestet") {
                bound {
                    minValue = 70
                    coverageUnits = CoverageUnit.INSTRUCTION
                }
            }
            rule("75% der Zeilen getestet") {
                bound {
                    minValue = 75
                    coverageUnits = CoverageUnit.LINE
                }
            }
        }
    }
}

tasks.register("alleDebugTests") {
    dependsOn(
        "detekt",
        "lintDebug",
        "koverVerifyDebug",
        "koverHtmlReportDebug", // nicht in der Pipeline, da es kein Test ist, erzeugt jedoch den Report
        "testDebugUnitTest",
        "connectedDebugAndroidTest"
    )
}

tasks.register("alleReleaseTests") {
    dependsOn(
        "detekt",
        "lint",
        "koverVerify",
        "koverHtmlReport",
        "test",
        "connectedCheck"
    )
}
