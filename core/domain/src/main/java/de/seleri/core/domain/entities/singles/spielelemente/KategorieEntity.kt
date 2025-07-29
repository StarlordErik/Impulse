package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.spielelementID.KategorieID
import kotlinx.serialization.Serializable

@Serializable
data class KategorieEntity(
	override val id: KategorieID,

	override val lokalisierungID: LokalisierungID,
	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	): SpielelementEntity
