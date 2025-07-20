package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementBasis
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil

data class Kartentext(
	val spielelementBasis: SpielelementBasis,

	val gesehen: Boolean,
	val besprochen: Boolean,
): Spielelement by spielelementBasis, Bestandteil
