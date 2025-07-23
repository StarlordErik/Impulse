plugins {
	kotlin("jvm")

	alias(libs.plugins.detekt)
}

kotlin {
	jvmToolchain(21)
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

dependencies {
	testImplementation(libs.junit)
}
