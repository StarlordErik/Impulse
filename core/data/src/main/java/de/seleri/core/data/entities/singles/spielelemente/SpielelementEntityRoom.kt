package de.seleri.core.data.entities.singles.spielelemente

import de.seleri.core.data.entities.singles.EntityRoom

interface SpielelementEntityRoom : EntityRoom {
	val spielelementBasis: SpielelementBasis

	val favorisiert get() = spielelementBasis.favorisiert
	val inaktiv get() = spielelementBasis.inaktiv
	val selbstErstellt get() = spielelementBasis.selbstErstellt
	val ogSprache get() = spielelementBasis.ogSprache
}
