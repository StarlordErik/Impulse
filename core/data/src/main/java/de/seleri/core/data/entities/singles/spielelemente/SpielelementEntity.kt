package de.seleri.core.data.entities.singles.spielelemente

import de.seleri.core.data.entities.singles.DatenbankObjektEntity

interface SpielelementEntity : DatenbankObjektEntity {
	val spielelementBasis: SpielelementBasis

	val favorisiert get() = spielelementBasis.favorisiert
	val inaktiv get() = spielelementBasis.inaktiv
	val selbstErstellt get() = spielelementBasis.selbstErstellt
	val ogSprache get() = spielelementBasis.ogSprache
}
