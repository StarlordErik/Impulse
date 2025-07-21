package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten

interface Spielelement: DatenbankObjekt {

	override val datenbankObjektDaten: DatenbankObjektDaten

	val spielelementDaten: SpielelementDaten

	val lokalisierungen get() = spielelementDaten.lokalisierungen

	val ogSprache get() = spielelementDaten.ogSprache
	val selbstErstellt get() = spielelementDaten.selbstErstellt
	val inaktiv get() = spielelementDaten.inaktiv
	val favorisiert get() = spielelementDaten.favorisiert
}
