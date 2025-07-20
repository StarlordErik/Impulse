package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Kategorie(
	val spielelementDaten: SpielelementDaten,

	val sammlungsBestandteile: SammlungsBestandteile<Kartentext>,
): Spielelement by spielelementDaten, Sammlung<Kartentext> by sammlungsBestandteile, Bestandteil {

	override fun karte(texteProKarte: Int): List<Kartentext> {
		// return texteProKarte an Kartentexten
		TODO("Not yet implemented")
	}
}
