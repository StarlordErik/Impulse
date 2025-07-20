package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.BestandteilFK
import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Kategorie(
	val spielelementDaten: SpielelementDaten,

	val sammlungsBestandteile: SammlungsBestandteile<BestandteilFK.KartentextID>,
): Spielelement by spielelementDaten, Sammlung by sammlungsBestandteile {

	override fun karte(texteProKarte: Int): List<BestandteilFK.KartentextID> {
		// return texteProKarte an Kartentexten
		TODO("Not yet implemented")
	}
}
