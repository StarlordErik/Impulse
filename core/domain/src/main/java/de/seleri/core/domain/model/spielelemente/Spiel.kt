package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Spiel(
	val spielelementDaten: SpielelementDaten,

	val sammlungsBestandteile: SammlungsBestandteile<Kategorie>,

	val texteProKarte: Int,
	val bildDateiname: String?,
): Spielelement by spielelementDaten, Sammlung<Kategorie> by sammlungsBestandteile {

	override fun karte(texteProKarte: Int): List<Kartentext> {
		// wähle eine Kategorie aus und return kategorie.karte(texteProKarte)
		TODO("Not yet implemented")
	}
}
