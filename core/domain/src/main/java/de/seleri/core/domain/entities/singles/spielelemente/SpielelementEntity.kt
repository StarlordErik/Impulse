package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.SpielelementIDint
import de.seleri.core.domain.entities.singles.Entity

interface SpielelementEntity: Entity {

	override val id: SpielelementIDint

	val lokalisierungID: LokalisierungIDint // Spielelemente sind eindeutig bzgl. ihrer LokalisierungID

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
