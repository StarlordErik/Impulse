package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.spielelementID.KartentextID
import kotlinx.serialization.Serializable

@Serializable
data class KartentextEntity(
	override val id: KartentextID,

	override val lokalisierungID: LokalisierungID,
	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	val gesehen: Boolean,
	val besprochen: Boolean,
): SpielelementEntity
