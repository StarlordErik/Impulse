package de.seleri.core.data.entities.singles.spielelemente

import de.seleri.core.common.Sprache

data class SpielelementBasis(
	val ogSprache: Sprache,
	val selbstErstellt: Boolean,
	val inaktiv: Boolean,
	val favorisiert: Boolean,
)
