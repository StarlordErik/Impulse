import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)

	alias(libs.plugins.detekt)
	alias(libs.plugins.kover)

	alias(libs.plugins.androidxComposeCompiler)
}

dependencies {
	implementation(project(":core:common"))
	implementation(project(":core:domain"))

	implementation(libs.androidx.core.ktx)
	androidTestImplementation(libs.androidx.junit)

	/* Jetpack Compose: */

	// um die Versionen von allen Compose-Abhängigkeiten über composeBOM zu regeln
	implementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(platform(libs.androidx.compose.bom))

	// Material Design 3: enthält die UI-Elemente und Farbverwaltung
	implementation(libs.androidx.material3)

	// Android Studio Preview support
	implementation(libs.androidx.ui.tooling.preview)
	debugImplementation(libs.androidx.ui.tooling)
	implementation(libs.mockk) // Mockk

	// UI Tests
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.test.manifest)

	// View-Models
	implementation(libs.androidx.lifecycle.viewmodel.compose)
}

kotlin {
	jvmToolchain(
		project
			.property("jdkVersion")
			.toString()
			.toInt()
	)
}

android {
	namespace = "de.seleri.core.representation"
	compileSdk = project
		.property("compileSdk")
		.toString()
		.toInt()

	defaultConfig {
		minSdk = project
			.property("minSdk")
			.toString()
			.toInt()
	}

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

	buildFeatures {
		compose = true
	}
}

kover.reports {
	filters.excludes {
		classes(
			"",
		)
		packages(
			"",
		)
	}
	verify {
		warningInsteadOfFailure = false

		val minimum = project
			.property("koverMinValue")
			.toString()
			.toInt()
		rule(
			"${
				project
					.property("koverRulePrefix")
					.toString()
			} $minimum${
				project
					.property("koverRuleSuffix")
					.toString()
			}"
		) {
			bound {
				minValue = minimum
				coverageUnits = CoverageUnit.BRANCH
			}
			bound {
				minValue = minimum
				coverageUnits = CoverageUnit.INSTRUCTION
			}
			bound {
				minValue = minimum
				coverageUnits = CoverageUnit.LINE
			}
		}
	}
}
