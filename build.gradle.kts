// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	alias(libs.plugins.jetbrainsKotlinJvm) apply false // für ein Kotlin-Modul (nicht mit Android kombinierbar)
	alias(libs.plugins.jetbrainsKotlinAndroid) apply false // für ein Android-Modul (nicht mit Kotlin kombinierbar)

	alias(libs.plugins.androidApplication) apply false // für ein Android-App-Modul
	alias(libs.plugins.androidLibrary) apply false // für ein Android-Lib-Modul

	alias(libs.plugins.detekt) apply false // Detekt

	alias(libs.plugins.ksp) apply false // KSP; benötigt für Hilt, Room
	alias(libs.plugins.hilt.android) apply false // Hilt
	alias(libs.plugins.room) apply false // Room
}
