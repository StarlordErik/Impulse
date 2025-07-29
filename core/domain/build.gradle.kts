import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
	alias(libs.plugins.jetbrainsKotlinJvm)

	alias(libs.plugins.detekt)
	alias(libs.plugins.kover)

	alias(libs.plugins.kotlinx.serialization)
}

dependencies {
	implementation(project(":core:common"))

	implementation(libs.kotlinx.serialization.json)

	testImplementation(libs.junit)
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
