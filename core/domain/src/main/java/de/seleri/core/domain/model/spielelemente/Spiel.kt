package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Spiel(
	val spielelementDaten: SpielelementDaten,

	val sammlungsBestandteile: SammlungsBestandteile<BestandteilID.KategorieID>,

	val texteProKarte: Int = 1,
	val bildDateiname: String? = null,
): Spielelement by spielelementDaten, Sammlung by sammlungsBestandteile
