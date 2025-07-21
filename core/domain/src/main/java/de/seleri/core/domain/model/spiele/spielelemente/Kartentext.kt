package de.seleri.core.domain.model.spiele.spielelemente

import de.seleri.core.domain.model.sammlungen.Bestandteil

data class Kartentext(
	private val spielelementDaten: SpielelementDaten,

	val gesehen: Boolean = false,
	val besprochen: Boolean = false,
): Spielelement by spielelementDaten, Bestandteil
