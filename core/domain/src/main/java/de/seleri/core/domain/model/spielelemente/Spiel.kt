package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

data class Spiel(
	val spielelementDaten: SpielelementDaten,

	val sammlungDaten: SammlungDaten<BestandteilID.KategorieID>,

	val texteProKarte: Int = 1,
	val bildDateiname: String? = null,
): Spielelement by spielelementDaten, Sammlung by sammlungDaten
