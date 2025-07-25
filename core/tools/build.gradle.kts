plugins {
	alias(libs.plugins.jetbrainsKotlinJvm)

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

java {
	toolchain {
		languageVersion.set(
			JavaLanguageVersion.of(
				project
					.property("jdkVersion")
					.toString()
					.toInt()
			)
		)
	}
}
