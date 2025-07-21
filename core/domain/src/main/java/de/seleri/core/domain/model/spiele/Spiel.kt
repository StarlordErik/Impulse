package de.seleri.core.domain.model.spiele

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.sammlungen.Sammlung
import de.seleri.core.domain.model.sammlungen.SammlungDaten
import de.seleri.core.domain.model.spiele.metadaten.SpielMeta
import de.seleri.core.domain.model.spiele.metadaten.SpielMetaDaten
import de.seleri.core.domain.model.spiele.spielelemente.Spielelement
import de.seleri.core.domain.model.spiele.spielelemente.SpielelementDaten

data class Spiel(
	private val spielelementDaten: SpielelementDaten,

	private val sammlungDaten: SammlungDaten<BestandteilID.KategorieID>,

	private val spielMetaDaten: SpielMetaDaten = SpielMetaDaten(),

	val anleitung: String? = null,
	val texteProKarte: Int = 1,
): Spielelement by spielelementDaten,
	Sammlung<BestandteilID.KategorieID> by sammlungDaten,
	SpielMeta by spielMetaDaten
