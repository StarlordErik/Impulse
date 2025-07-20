package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Kategorie(
	val spielelementDaten: SpielelementDaten,

	val sammlungsBestandteile: SammlungsBestandteile<BestandteilID.KartentextID>,
): Spielelement by spielelementDaten, Sammlung by sammlungsBestandteile, Bestandteil {

	override fun karte(texteProKarte: Int): List<BestandteilID.KartentextID> {
		// return texteProKarte an Kartentexten
		TODO("Not yet implemented")
	}
}
