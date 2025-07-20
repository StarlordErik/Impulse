package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementBasis
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Kategorie(
	val spielelementBasis: SpielelementBasis,

	val sammlungsBestandteile: SammlungsBestandteile<Kartentext>,
): Spielelement by spielelementBasis, Sammlung<Kartentext> by sammlungsBestandteile, Bestandteil {

	override fun karte(texteProKarte: Int): List<Kartentext> {
		TODO("Not yet implemented")
	}

	override fun wurdeGesehen() {
		TODO("Not yet implemented")
	}
}
