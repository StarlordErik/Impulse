plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)

	alias(libs.plugins.detekt)

	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt.android)

	alias(libs.plugins.androidxComposeCompiler)
}

dependencies {
	implementation(project(":core:common"))
	implementation(project(":core:di"))
	implementation(project(":core:representation"))

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.activity)
	// implementation(libs.androidx.appcompat)
	// implementation(libs.androidx.constraintlayout)

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)

	implementation(libs.androidx.navigation.compose)
	implementation(libs.androidx.navigation.testing.android)
	implementation(libs.androidx.hilt.navigation.compose)

// 	implementation(libs.compose.ui)
// 	implementation(libs.compose.material3)
// 	implementation(libs.compose.ui.tooling.preview)
// 	implementation(libs.activity.compose)
// 	implementation(libs.androidx.lifecycle.runtime.compose)
//
// 	implementation(libs.androidx.junit.ktx)
// 	implementation(libs.androidx.ui.test.junit4.android)
// 	implementation(libs.androidx.lifecycle.viewmodel.compose)

//	testImplementation(libs.androidx.core)

	androidTestImplementation(libs.androidx.junit)

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
	namespace = "de.seleri.app"
	compileSdk = project
		.property("compileSdk")
		.toString()
		.toInt()

	defaultConfig {
		applicationId = "de.seleri.app"
		minSdk = project
			.property("minSdk")
			.toString()
			.toInt()
		targetSdk = project
			.property("compileSdk")
			.toString()
			.toInt()
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	packaging {
		resources {
			excludes += "/META-INF/LICENSE.md"
			excludes += "/META-INF/LICENSE-notice.md"
		}
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

	@Suppress("UnstableApiUsage") composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.kotlin.get()
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
		"detekt", "lint", "koverVerify", "koverHtmlReport", "test", "connectedCheck"
	)
}
