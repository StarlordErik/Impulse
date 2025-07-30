package de.seleri.core.data.entities.singles.lokStern.spielelemente

import de.seleri.core.common.id.spielelementID.SpielelementID
import de.seleri.core.data.entities.singles.lokStern.LokSternRoom

interface SpielelementRoom: LokSternRoom {

	override val id: SpielelementID

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
