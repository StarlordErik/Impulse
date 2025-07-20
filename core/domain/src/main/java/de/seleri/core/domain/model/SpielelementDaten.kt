package de.seleri.core.domain.model

import de.seleri.core.common.Sprache

abstract class SpielelementDaten(
	override val id: Int,
	override val lokalisierungen: Collection<Int>,

	val ogSprache: Sprache,
	val selbstErstellt: Boolean,
	val inaktiv: Boolean,
	val favorisiert: Boolean,
): Spielelement
