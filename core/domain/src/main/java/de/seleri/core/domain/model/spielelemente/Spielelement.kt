package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.DatenbankObjekt

interface Spielelement: DatenbankObjekt {

	val spielelementDaten: SpielelementDaten

	val lokalisierungen get() = spielelementDaten.lokalisierungen

	val ogSprache get() = spielelementDaten.ogSprache
	val selbstErstellt get() = spielelementDaten.selbstErstellt
	val inaktiv get() = spielelementDaten.inaktiv
	val favorisiert get() = spielelementDaten.favorisiert
}
