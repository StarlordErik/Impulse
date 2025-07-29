package de.seleri.core.data.entities.singles.spielelemente

import kotlinx.serialization.Serializable

@Serializable
data class SpielelementDatenRoom(
	val selbstErstellt: Boolean,
	val inaktiv: Boolean,
	val favorisiert: Boolean,
)
