import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
}

android {
    namespace = "de.seleri.impulse"
    compileSdk = 35

    defaultConfig {
        applicationId = "de.seleri.impulse"
        minSdk = 29
        targetSdk = 35
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
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.snakeyaml)
    testImplementation(libs.junit)
    testImplementation(libs.mokk)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kover {
    reports {
        filters {
            excludes {
                classes("de.seleri.tools.*")
            }
        }
        verify {
            warningInsteadOfFailure = true // TODO auf false setzen, damit Kover aktiviert wird

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

tasks.register("alleTests") {
    dependsOn("detekt", "lint", "koverVerify", "testDebugUnitTest", "testReleaseUnitTest", "connectedAndroidTest")
}
