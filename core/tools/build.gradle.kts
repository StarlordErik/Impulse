plugins {
	application
	alias(libs.plugins.jetbrainsKotlinJvm)

	alias(libs.plugins.detekt)
}

dependencies {
	implementation(project(":core:common"))
	implementation(project(":core:domain"))
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
