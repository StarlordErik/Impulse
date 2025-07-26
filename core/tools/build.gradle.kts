plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)

	alias(libs.plugins.detekt)
}

dependencies {
	implementation(project(":core:common"))
	implementation(project(":core:domain"))
	implementation(project(":core:repository"))
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
	namespace = "de.seleri.core.tools"
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
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}

	lint {
		warningsAsErrors = true
	}
}
