package de.seleri.core.data.entities.singles

import de.seleri.core.common.Sprache

data class Basis(
	val ogSprache: Sprache = Sprache.DE,
	val inaktiv: Boolean = false,
	val selbstErstellt: Boolean = false,
	val favorisiert: Boolean = false,
)
