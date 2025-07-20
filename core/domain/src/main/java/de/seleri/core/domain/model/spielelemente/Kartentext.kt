package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementDaten

data class Kartentext(
	val spielelementDaten: SpielelementDaten,

	val gesehen: Boolean,
	val besprochen: Boolean,
): Spielelement by spielelementDaten, Bestandteil
