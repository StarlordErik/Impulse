package de.seleri.core.data.entities.singles

import de.seleri.core.common.Sprache

data class SpielelementBasis(
	val ogSprache: Sprache = Sprache.DE,
	val selbstErstellt: Boolean = false,
	val inaktiv: Boolean = false,
	val favorisiert: Boolean = false,
)
