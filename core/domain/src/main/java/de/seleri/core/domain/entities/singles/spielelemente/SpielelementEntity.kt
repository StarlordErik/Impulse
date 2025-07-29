package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.spielelementID.SpielelementID
import de.seleri.core.domain.entities.singles.Entity

interface SpielelementEntity: Entity {

	override val id: SpielelementID

	val lokalisierungID: LokalisierungID // Spielelemente sind eindeutig bzgl. ihrer LokalisierungID

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
