package de.seleri.core.domain.model

import de.seleri.core.common.Sprache

abstract class SpielelementBasis(
	override val id: Int,
	override val lokalisierungen: Collection<Lokalisierung>,

	val ogSprache: Sprache,
	val inaktiv: Boolean,
	val selbstErstellt: Boolean,
	val favorisiert: Boolean,
): Spielelement {

}
