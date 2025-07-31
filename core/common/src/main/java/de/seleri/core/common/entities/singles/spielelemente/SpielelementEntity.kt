package de.seleri.core.common.entities.singles.spielelemente

import de.seleri.core.common.entities.LokSternEntity
import de.seleri.core.common.entities.singles.SingleEntity
import de.seleri.core.common.ids.spielelementID.SpielelementID

interface SpielelementEntity: SingleEntity, LokSternEntity {

	override val id: SpielelementID

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
