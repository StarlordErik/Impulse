package de.seleri.core.data.entities.singles.spielelemente

import de.seleri.core.common.id.spielelementID.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungStern

interface SpielelementRoom: LokalisierungStern {

	override val id: SpielelementID

	override val lokalisierungID: Int

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
