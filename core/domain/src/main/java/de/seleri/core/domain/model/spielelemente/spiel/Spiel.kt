package de.seleri.core.domain.model.spielelemente.spiel

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.Spielelement
import de.seleri.core.domain.model.spielelemente.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

data class Spiel(
	private val spielelementDaten: SpielelementDaten,

	private val sammlungDaten: SammlungDaten<BestandteilID.KategorieID>,

	private val spielMetaDaten: SpielMetaDaten = SpielMetaDaten(),

	val anleitung: String? = null,
	val texteProKarte: Int = 1,
): Spielelement by spielelementDaten,
	Sammlung<BestandteilID.KategorieID> by sammlungDaten,
	SpielMeta by spielMetaDaten
