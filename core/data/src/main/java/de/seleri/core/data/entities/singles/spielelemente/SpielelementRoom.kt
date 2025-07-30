package de.seleri.core.data.entities.singles.spielelemente

import de.seleri.core.data.entities.EntityRoom

interface SpielelementRoom: EntityRoom {

	val lokalisierungID: Int

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
