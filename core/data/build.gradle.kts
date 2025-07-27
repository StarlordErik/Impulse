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

dependencies {
	implementation(project(":core:common"))
	implementation(project(":core:domain"))

	testImplementation(libs.junit)

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)

	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)
	testImplementation(libs.androidx.room.testing)
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
