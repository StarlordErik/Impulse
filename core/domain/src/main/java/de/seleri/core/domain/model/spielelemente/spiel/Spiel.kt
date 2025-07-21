package de.seleri.core.domain.model.spielelemente.spiel

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

data class Spiel(
	private val spielMetaDaten: SpielMetaDaten,

	val anleitung: String? = null,
	val texteProKarte: Int = 1,

	private val sammlungDaten: SammlungDaten<BestandteilID.KategorieID>,
): SpielMeta by spielMetaDaten, Sammlung<BestandteilID.KategorieID> by sammlungDaten
