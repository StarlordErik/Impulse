import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.androidLibrary)

	alias(libs.plugins.detekt)
	alias(libs.plugins.kover)

	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt.android)

	alias(libs.plugins.room)
}

android {
	namespace = "de.seleri.core.data"
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

	room {
		schemaDirectory("$projectDir/schemas")
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
	implementation(project(":core:common"))

	testImplementation(libs.junit)

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)

	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)
	testImplementation(libs.androidx.room.testing)
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
