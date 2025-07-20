package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.DatenbankObjektID
import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

data class Kategorie(
	val spielelementDaten: SpielelementDaten,

	val sammlungsBestandteile: SammlungsBestandteile<DatenbankObjektID.KartentextID>,
): Spielelement by spielelementDaten, Sammlung by sammlungsBestandteile {

	override fun karte(texteProKarte: Int): List<DatenbankObjektID.KartentextID> {
		// return texteProKarte an Kartentexten
		TODO("Not yet implemented")
	}
}
