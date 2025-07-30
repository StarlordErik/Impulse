package de.seleri.core.data.entities.singles.spielelemente

import de.seleri.core.data.entities.singles.IDEntityRoom

interface SpielelementRoom: IDEntityRoom {

	val lokalisierungID: Int

	val spielelementDatenRoom: SpielelementDatenRoom

	val selbstErstellt get() = spielelementDatenRoom.selbstErstellt
	val inaktiv get() = spielelementDatenRoom.inaktiv
	val favorisiert get() = spielelementDatenRoom.favorisiert
}
