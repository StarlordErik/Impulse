import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsKotlinAndroid)

  alias(libs.plugins.detekt)
  alias(libs.plugins.kover)

  alias(libs.plugins.ksp)
  alias(libs.plugins.room)
}

android {
  namespace = "de.seleri.core.data"
  compileSdk = 36

  defaultConfig {
    applicationId = "de.seleri.kern.data"
    minSdk = 29
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  lint {
    warningsAsErrors = true
  }

  room {
    schemaDirectory("$projectDir/schemas")
  }
}

kotlin {
  compilerOptions {
    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)

  implementation(libs.androidx.room.runtime)
  ksp(libs.androidx.room.compiler)
  implementation(libs.androidx.room.ktx)
  testImplementation(libs.androidx.room.testing)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}

kover {
  reports {
    filters {
      excludes {
        classes(
          "",
        )
        packages(
          "",
        )
      }
    }
    verify {
      warningInsteadOfFailure = false

      rule("genug Abzweigungen getestet") {
        bound {
          minValue = 66
          coverageUnits = CoverageUnit.BRANCH
        }
      }
      rule("genug Anweisungen getestet") {
        bound {
          minValue = 66
          coverageUnits = CoverageUnit.INSTRUCTION
        }
      }
      rule("genug Zeilen getestet") {
        bound {
          minValue = 66
          coverageUnits = CoverageUnit.LINE
        }
      }
    }
  }
}
