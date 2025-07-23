import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)

	alias(libs.plugins.detekt)
	alias(libs.plugins.kover)
}

android {
	namespace = "de.seleri.core.representation"
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
		val jdkVersion = project
			.property("jdkVersion")
			.toString()
			.toInt()
		sourceCompatibility = JavaVersion.toVersion(jdkVersion)
		targetCompatibility = JavaVersion.toVersion(jdkVersion)
	}

	lint {
		warningsAsErrors = true
	}
}

kotlin {
	jvmToolchain(
		project
			.property("jdkVersion")
			.toString()
			.toInt()
	)
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

	implementation(project(":core:common"))
	implementation(project(":core:domain"))
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
