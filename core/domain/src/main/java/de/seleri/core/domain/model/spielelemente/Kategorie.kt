package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementDaten
import de.seleri.core.domain.model.sammlungen.Bestandteil
import de.seleri.core.domain.model.sammlungen.Sammlung
import de.seleri.core.domain.model.sammlungen.SammlungsBestandteile

data class Kategorie(
	val spielelementDaten: SpielelementDaten,

	val sammlungsBestandteile: SammlungsBestandteile<Bestandteil.KartentextID>,
): Spielelement by spielelementDaten, Sammlung by sammlungsBestandteile {

	override fun karte(texteProKarte: Int): List<Bestandteil.KartentextID> {
		// return texteProKarte an Kartentexten
		TODO("Not yet implemented")
	}
}
