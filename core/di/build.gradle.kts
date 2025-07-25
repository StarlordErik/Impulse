plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)

	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt.android)
}

dependencies {
	implementation(project(":core:common"))
	implementation(project(":core:data"))
	implementation(project(":core:repository"))
	implementation(project(":core:domain"))
	implementation(project(":core:representation"))

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)

	implementation(libs.androidx.room.runtime)
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
	namespace = "de.seleri.core.di"
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
