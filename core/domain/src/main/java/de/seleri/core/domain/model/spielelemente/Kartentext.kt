package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Bestandteil

data class Kartentext(
	val spielelementDaten: SpielelementDaten,

	val gesehen: Boolean,
	val besprochen: Boolean,
): Spielelement by spielelementDaten, Bestandteil
