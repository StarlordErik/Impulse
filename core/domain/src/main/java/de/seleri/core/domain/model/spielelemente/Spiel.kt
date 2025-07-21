package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

data class Spiel(
	override val spielelementDaten: SpielelementDaten,

	override val sammlungDaten: SammlungDaten<BestandteilID.KategorieID>,

	val anleitung: String? = null,
	val texteProKarte: Int = 1,
	val bildDateiname: String? = null,
): Spielelement by spielelementDaten, Sammlung<BestandteilID.KategorieID> by sammlungDaten
