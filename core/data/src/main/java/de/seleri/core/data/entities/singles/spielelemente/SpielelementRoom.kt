package de.seleri.core.data.entities.singles.spielelemente

import de.seleri.core.data.entities.singles.EntityRoom

interface SpielelementRoom: EntityRoom {

	val spielelementDatenRoom: SpielelementDatenRoom

	val favorisiert get() = spielelementDatenRoom.favorisiert
	val inaktiv get() = spielelementDatenRoom.inaktiv
	val selbstErstellt get() = spielelementDatenRoom.selbstErstellt
}
