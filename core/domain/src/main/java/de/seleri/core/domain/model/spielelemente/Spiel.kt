package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementBasis
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Spiel(
	val spielelementBasis: SpielelementBasis,

	val sammlungsBestandteile: SammlungsBestandteile<Kategorie>,

	val texteProKarte: Int,
	val bildDateiname: String?,
): Spielelement by spielelementBasis, Sammlung<Kategorie> by sammlungsBestandteile {

	override fun karte(texteProKarte: Int): List<Kartentext> {
		TODO("Not yet implemented")
	}
}
