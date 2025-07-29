plugins {
	application
	alias(libs.plugins.jetbrainsKotlinJvm)

	alias(libs.plugins.detekt)

	alias(libs.plugins.kotlinx.serialization)
}

dependencies {
	implementation(project(":core:common"))
	implementation(project(":core:domain"))

	implementation(libs.kotlinx.serialization.json)
}

kotlin {
	jvmToolchain(
		project
			.property("jdkVersion")
			.toString()
			.toInt()
	)
}

application {
	mainClass.set("de.seleri.core.tools.NeuesSpielKt")
}
