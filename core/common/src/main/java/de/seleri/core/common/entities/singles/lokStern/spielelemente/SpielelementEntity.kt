package de.seleri.core.common.entities.singles.lokStern.spielelemente

import de.seleri.core.common.entities.singles.lokStern.LokSternEntity
import de.seleri.core.common.id.spielelementID.SpielelementID

interface SpielelementEntity: LokSternEntity {

	override val id: SpielelementID

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
