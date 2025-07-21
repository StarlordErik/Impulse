package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Bestandteil

data class Kartentext(
	override val spielelementDaten: SpielelementDaten,

	val gesehen: Boolean = false,
	val besprochen: Boolean = false,
): Spielelement by spielelementDaten, Bestandteil
