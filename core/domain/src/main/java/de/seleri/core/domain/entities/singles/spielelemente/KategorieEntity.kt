package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.idInt.KategorieIDint
import de.seleri.core.common.idInt.LokalisierungIDint
import kotlinx.serialization.Serializable

@Serializable
data class KategorieEntity(
	override val id: KategorieIDint,

	override val lokalisierungID: LokalisierungIDint,
	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	): SpielelementEntity
