package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Spiel(
	val spielelementDaten: SpielelementDaten,

	val sammlungsBestandteile: SammlungsBestandteile<BestandteilID.KategorieID>,

	val texteProKarte: Int,
	val bildDateiname: String?,
): Spielelement by spielelementDaten, Sammlung by sammlungsBestandteile {

	override fun karte(texteProKarte: Int): List<BestandteilID.KartentextID> {
		// wähle eine Kategorie aus und return kategorie.karte(texteProKarte)
		TODO("Not yet implemented")
	}
}
