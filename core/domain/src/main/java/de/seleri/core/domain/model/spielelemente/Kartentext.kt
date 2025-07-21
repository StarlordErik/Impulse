package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.DEFAULT_ID

class Kartentext(
	override val id: Int = DEFAULT_ID,
	private val spielelementDaten: SpielelementDaten,

	val gesehen: Boolean = false,
	val besprochen: Boolean = false,
): Spielelement by spielelementDaten, Bestandteil
