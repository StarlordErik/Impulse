import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
	kotlin("jvm")

	alias(libs.plugins.detekt)
	alias(libs.plugins.kover)
}

dependencies {
	testImplementation(libs.junit)
}

kover {
	reports {
		filters {
			excludes {
				classes(
					"",
				)
				packages(
					"",
				)
			}
		}
		verify {
			warningInsteadOfFailure = false

			val min = project
				.property("koverMinValue")
				.toString()
				.toInt()
			rule("Mindestens $min% getestet!") {
				bound {
					minValue = min
					coverageUnits = CoverageUnit.BRANCH
				}
				bound {
					minValue = min
					coverageUnits = CoverageUnit.INSTRUCTION
				}
				bound {
					minValue = min
					coverageUnits = CoverageUnit.LINE
				}
			}
		}
	}
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
