import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)

	alias(libs.plugins.detekt)
	alias(libs.plugins.kover)

	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt.android)
}

android {
	namespace = "de.seleri.core.repository"
	compileSdk = project
		.property("compileSdk")
		.toString()
		.toInt()

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
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

	implementation(project(":core:common"))
	implementation(project(":core:data"))
	implementation(project(":core:domain"))

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)
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
